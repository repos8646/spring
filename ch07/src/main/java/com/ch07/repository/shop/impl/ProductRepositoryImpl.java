package com.ch07.repository.shop.impl;

import com.ch07.entity.shop.Product;
import com.ch07.entity.shop.QProduct;
import com.ch07.repository.shop.ProductRepository;
import com.ch07.repository.shop.custom.ProductRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private QProduct qProduct = QProduct.product;

    public List<Product> selectProduct(){
        return queryFactory.select(qProduct).from(qProduct).fetch();
    }
    public Product selectProduct(int prodNo){
        return queryFactory
                .selectFrom(qProduct)
                .where(qProduct.prodNo.eq(prodNo))
                .fetchOne();
    }

    public List<Product> searchProduct(String nameCondition, int stockCondition){
        return null;
    }
    public List<Product> searchKeyword(String keyword){
        return null;
    }

}
