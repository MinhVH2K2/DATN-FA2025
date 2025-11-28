package com.example.DATN2025.repository;

import com.example.DATN2025.dto.Product.SearchProductDto;
import com.example.DATN2025.entity.Product;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {
    private SearchProductDto searchProductDto;

    public ProductSpecification(SearchProductDto searchRequest) {
        this.searchProductDto = searchRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        List<Predicate> predicates = new ArrayList<>();
        Join<Object, Object> detailJoin = root.join("productDetails", JoinType.INNER);
        predicates.add(criteriaBuilder.equal(detailJoin.get("deleteFlag"), false));
        if (searchProductDto.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchProductDto.getMinPrice()));
        }

        if (searchProductDto.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchProductDto.getMaxPrice()));
        }

        if (searchProductDto.getProductName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+searchProductDto.getProductName()+"%"));
        }

        if(searchProductDto.getCode() != null) {
            predicates.add(criteriaBuilder.like(root.get("code"), "%"+searchProductDto.getCode()+"%"));

        }

        if(searchProductDto.getKeyword() != null) {
            String keyword = searchProductDto.getKeyword();

            Predicate productNamePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate productCodePredicate = criteriaBuilder.like(root.get("code"), "%" + keyword + "%");
            Predicate combinedPredicate = criteriaBuilder.or(productNamePredicate, productCodePredicate);

            predicates.add(combinedPredicate);

        }

//

       // predicates.add(criteriaBuilder.equal(root.get("status"), 1));
      //  predicates.add(criteriaBuilder.equal(root.get("deleteFlag"), false));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
