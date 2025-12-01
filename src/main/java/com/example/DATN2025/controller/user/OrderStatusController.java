package com.example.DATN2025.controller.user;



import com.example.DATN2025.dto.Cart.CartDto;
import com.example.DATN2025.entity.Bill;
import com.example.DATN2025.service.BillService;
import com.example.DATN2025.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderStatusController {
    private final BillService billService;
    private final CartService cartService;

    public OrderStatusController(BillService billService, CartService cartService) {
        this.billService = billService;
        this.cartService = cartService;
    }

    @GetMapping("/cart-status")
    public String viewCartStatus(Model model,
                                 @RequestParam(required = false) String status,
                                 @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)  Pageable pageable) {

        Page<Bill> billPage; // Khởi tạo billPage không cần gán null

        if(status == null || status.trim().isEmpty()) {
            billPage = billService.getBillByAccount(pageable);
        } else {
            billPage = billService.getBillByStatus(status, pageable);
            model.addAttribute("status", status);
        }

        // ⚠️ BƯỚC KHẮC PHỤC QUAN TRỌNG: Kiểm tra và xử lý null
        if (billPage == null) {
            // Gán nó bằng một Page rỗng để tránh lỗi NullPointerException trong Thymeleaf
            // Page.empty(pageable) được khuyến nghị nếu dùng Spring Data
            // Nếu không, bạn có thể dùng new PageImpl<>(Collections.emptyList(), pageable, 0)
            billPage = Page.empty(pageable);
        }

        model.addAttribute("bills", billPage);
        return "user/cart-status";
    }

    @PostMapping("/cancel-bill/{id}")
    public String cancelBill(@PathVariable Long id) {
        billService.updateStatus("HUY", id);
        return "redirect:/cart-status";
    }

    @ResponseBody
    @GetMapping("/api/getAllCart")
    public List<CartDto> getAllCart() {
        return cartService.getAllCartByAccountId();
    }

}
