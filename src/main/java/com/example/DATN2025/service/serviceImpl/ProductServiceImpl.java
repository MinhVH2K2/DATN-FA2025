package com.example.DATN2025.service.serviceImpl;


import com.example.DATN2025.dto.Product.ProductDetailDto;
import com.example.DATN2025.dto.Product.ProductDto;
import com.example.DATN2025.dto.Product.ProductSearchDto;
import com.example.DATN2025.dto.Product.SearchProductDto;
import com.example.DATN2025.entity.Image;
import com.example.DATN2025.entity.Product;
import com.example.DATN2025.entity.ProductDetail;
import com.example.DATN2025.entity.ProductDiscount;
import com.example.DATN2025.exception.NotFoundException;
import com.example.DATN2025.exception.ShopApiException;
import com.example.DATN2025.repository.ProductDetailRepository;
import com.example.DATN2025.repository.ProductDiscountRepository;
import com.example.DATN2025.repository.ProductRepository;
import com.example.DATN2025.repository.ProductSpecification;
import com.example.DATN2025.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductDiscountRepository productDiscountRepository;

    @Override
    public Page<Product> getAllProduct(Pageable pageable0) {
        return productRepository.findAll(pageable0);
    }

    @Override
    public Page<ProductSearchDto> getAll(Pageable pageable) {

        return productRepository.getAll(pageable);
    }

    @Override
    public Product save(Product product) throws IOException {

        // Kiểm tra mã sản phẩm rỗng hoặc null → tự sinh mã
        if (!productRepository.existsByCode(product.getCode())) {
            product.setDeleteFlag(false);
            product.setCreateDate(LocalDateTime.now());
            product.setUpdatedDate(LocalDateTime.now());
            return productRepository.save(product);
        }
        else {
            Product existingProduct = productRepository.findByCode(product.getCode());
            System.out.print(existingProduct.getCode());
                    if (existingProduct != null) {

            // --- UPDATE SẢN PHẨM ---
            existingProduct.setCode(existingProduct.getCode());
            existingProduct.setName(product.getName());
            existingProduct.setDescribe(product.getDescribe());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setCreateDate(existingProduct.getCreateDate());
            existingProduct.setGender(product.getGender());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setUpdatedDate(LocalDateTime.now());
            existingProduct.setDeleteFlag(product.isDeleteFlag());

            // --- CẬP NHẬT DANH SÁCH CHI TIẾT ---
            if (product.getProductDetails() != null && !product.getProductDetails().isEmpty()) {
                for (ProductDetail pd : product.getProductDetails()) {
                    pd.setProduct(existingProduct);
                }
                existingProduct.setProductDetails(product.getProductDetails());
            }

            // --- CẬP NHẬT HÌNH ẢNH ---
            if (product.getImage() != null && !product.getImage().isEmpty()) {
                for (Image img : product.getImage()) {
                    img.setProduct(existingProduct);
                }
                existingProduct.setImage(product.getImage());
            }

            // --- TÍNH GIÁ THẤP NHẤT ---
            Double minPrice = product.getProductDetails() != null && !product.getProductDetails().isEmpty()
                    ? product.getProductDetails().stream()
                    .map(ProductDetail::getPrice)
                    .filter(Objects::nonNull)
                    .min(Double::compareTo)
                    .orElse(0.0)
                    : 0.0;

            existingProduct.setPrice(minPrice);
           // System.out.print(existingProduct.getCode());
            return productRepository.save(existingProduct);

        }


       //  Kiểm tra xem sản phẩm này đã tồn tại (theo code)
//        Product existingProduct = productRepository.findByCode(product.getCode());
//        System.out.print(existingProduct.getCode());
//        if (existingProduct != null) {
//
//            // --- UPDATE SẢN PHẨM ---
//            existingProduct.setCode(existingProduct.getCode());
//            existingProduct.setName(product.getName());
//            existingProduct.setDescribe(product.getDescribe());
//            existingProduct.setCategory(product.getCategory());
//           // existingProduct.setClothingType(product.getClothingType());
//            existingProduct.setGender(product.getGender());
//            existingProduct.setBrand(product.getBrand());
//            existingProduct.setUpdatedDate(LocalDateTime.now());
//            existingProduct.setDeleteFlag(product.isDeleteFlag());
//
//            // --- CẬP NHẬT DANH SÁCH CHI TIẾT ---
//            if (product.getProductDetails() != null && !product.getProductDetails().isEmpty()) {
//                for (ProductDetail pd : product.getProductDetails()) {
//                    pd.setProduct(existingProduct);
//                }
//                existingProduct.setProductDetails(product.getProductDetails());
//            }
//
//            // --- CẬP NHẬT HÌNH ẢNH ---
//            if (product.getImage() != null && !product.getImage().isEmpty()) {
//                for (Image img : product.getImage()) {
//                    img.setProduct(existingProduct);
//                }
//                existingProduct.setImage(product.getImage());
//            }
//
//            // --- TÍNH GIÁ THẤP NHẤT ---
//            Double minPrice = product.getProductDetails() != null && !product.getProductDetails().isEmpty()
//                    ? product.getProductDetails().stream()
//                    .map(ProductDetail::getPrice)
//                    .filter(Objects::nonNull)
//                    .min(Double::compareTo)
//                    .orElse(0.0)
//                    : 0.0;
//
//            existingProduct.setPrice(minPrice);
//           // System.out.print(existingProduct.getCode());
//            return productRepository.save(existingProduct);
//
//        } else {
//            // --- TẠO MỚI SẢN PHẨM ---
////            Double minPrice = product.getProductDetails() != null && !product.getProductDetails().isEmpty()
////                    ? product.getProductDetails().stream()
////                    .map(ProductDetail::getPrice)
////                    .filter(Objects::nonNull)
////                    .min(Double::compareTo)
////                    .orElse(0.0)
////                    : 0.0;
////
////            product.setPrice(minPrice);
////            product.setDeleteFlag(false);
////            product.setCreateDate(LocalDateTime.now());
////            product.setUpdatedDate(LocalDateTime.now());
////
////            if (product.getProductDetails() != null) {
////                for (ProductDetail pd : product.getProductDetails()) {
////                    pd.setProduct(product);
////                }
////            }
////
////            if (product.getImage() != null) {
////                for (Image img : product.getImage()) {
////                    img.setProduct(product);
////                }
////            }
//
//           return   productRepository.save(product);
//        }

    }
        return product;
    }

    @Override
    public Product delete(Long id)  {
        Product product = productRepository.findById(id).orElseThrow( () -> new NotFoundException("Product not found"));
        product.setDeleteFlag(true);
        return productRepository.save(product);
    }

    @Override
    public Product getProductByCode(String code) {
        Product product = productRepository.findByCode(code);
        if(product != null) {

            return product;
        }
        return null;
    }

    @Override
    public boolean existsByCode(String code) {
        return productRepository.existsByCode(code);
    }

    public Page<Product> search(String productName, Pageable pageable) {
        Page<Product> page = productRepository.searchProductName(productName, pageable);
        return page;
    }

    @Override
    public Page<ProductSearchDto> listSearchProduct(String maSanPham, String tenSanPham, Long nhanHang, Long chatLieu, Long theLoai,Integer trangThai, Pageable pageable) {
        Page<ProductSearchDto> productSearchDtos = productRepository.listSearchProduct(maSanPham,tenSanPham,nhanHang,chatLieu,theLoai,trangThai,pageable);
        return productSearchDtos;
    }

    @Override
    public Page<Product> getAllByStatus(int status, Pageable pageable) {
        return productRepository.findAllByStatusAndDeleteFlag(status, false, pageable);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<ProductDto> searchProduct(SearchProductDto searchDto, Pageable pageable) {
        Specification<Product> spec = new ProductSpecification(searchDto);
        Page<Product> products = productRepository.findAll(spec, pageable);
        return products.map(this::convertToDto);
    }

    @Override
    public Page<ProductDto> getAllProductApi(Pageable pageable) {
        Page<Product> productPage = productRepository.findAllByDeleteFlagFalse(pageable);
        return productPage.map(this::convertToDto);
    }

    @Override
    public ProductDto getProductByBarcode(String barcode) {
        ProductDetail productDetail = productDetailRepository.findByBarcodeContainingIgnoreCase(barcode);
        if(productDetail == null) {
            throw  new ShopApiException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm có mã vạch: " + barcode);
        }
        Product product = productDetail.getProduct();
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setImageUrl(product.getImage().get(0).getLink());
        productDto.setDescription(product.getDescribe());
        productDto.setPriceMin(product.getProductDetails().get(0).getPrice());
        productDto.setCreateDate(product.getCreateDate());
        productDto.setUpdatedDate(product.getUpdatedDate());

        List<ProductDetailDto> productDetailDtoList = new ArrayList<>();

        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productDetail.getId());
        productDetailDto.setProductId(product.getId());
        productDetailDto.setColor(productDetail.getColor());
        productDetailDto.setSize(productDetail.getSize());
        productDetailDto.setPrice(productDetail.getPrice());
        productDetailDto.setQuantity(productDetail.getQuantity());
        productDetailDto.setBarcode(productDetail.getBarcode());
        productDetailDtoList.add(productDetailDto);
        ProductDiscount productDiscount = productDiscountRepository.findValidDiscountByProductDetailId(productDetail.getId());
        if(productDiscount != null) {
//            Date endDate = productDiscount.getEndDate();
//            Date currentDate = new Date();
//            if (currentDate.compareTo(endDate) > 0) {
//            }
            productDetailDto.setDiscountedPrice(productDiscount.getDiscountedAmount());

        }
        productDto.setProductDetailDtos(productDetailDtoList);

        return productDto;
    }

    @Override
    public List<ProductDto> getAllProductNoPaginationApi(SearchProductDto searchRequest) {
        Specification<Product> spec = new ProductSpecification(searchRequest);
        List<Product> products = productRepository.findAll(spec);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getByProductDetailId(Long detailId) {
        return convertToDto(productRepository.findByProductDetail_Id(detailId));
    }


    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setImageUrl(product.getImage().get(0).getLink());
        productDto.setDescription(product.getDescribe());
        productDto.setCreateDate(product.getCreateDate());
        productDto.setUpdatedDate(product.getUpdatedDate());

        List<ProductDetailDto> productDetailDtoList = new ArrayList<>();
        Double priceMin = Double.valueOf(100000000);


        for (ProductDetail productDetail:
             product.getProductDetails()) {
            if (productDetail.getDeleteFlag()) continue;
            if(productDetail.getPrice() < priceMin) {
                priceMin = productDetail.getPrice();
            }
            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setId(productDetail.getId());
            productDetailDto.setProductId(product.getId());
            productDetailDto.setColor(productDetail.getColor());
            productDetailDto.setSize(productDetail.getSize());
            productDetailDto.setPrice(productDetail.getPrice());
            productDetailDto.setQuantity(productDetail.getQuantity());
            productDetailDto.setBarcode(productDetail.getBarcode());
            ProductDiscount productDiscount = productDiscountRepository.findValidDiscountByProductDetailId(productDetail.getId());
            if(productDiscount != null) {
                productDto.setDiscounted(true);
                productDetailDto.setDiscountedPrice(productDiscount.getDiscountedAmount());
                if (productDiscount.getDiscountedAmount() < priceMin) {
                    priceMin = productDiscount.getDiscountedAmount();
                }
            }
            productDetailDtoList.add(productDetailDto);
        }
        productDto.setPriceMin(priceMin);
        productDto.setProductDetailDtos(productDetailDtoList);
        return productDto;
    }

}
