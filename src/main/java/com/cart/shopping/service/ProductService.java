package com.cart.shopping.service;

import java.util.List;

import com.cart.shopping.model.Product;
import com.cart.shopping.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }
    public void removeProduct(Long id)
    {
        productRepository.deleteById(id);
    }
    public Product getProduct(Long id)
    {
        return productRepository.findById(id).get();
    }
}
