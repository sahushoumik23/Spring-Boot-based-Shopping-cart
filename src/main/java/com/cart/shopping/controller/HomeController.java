package com.cart.shopping.controller;

import com.cart.shopping.service.ProductService;
import com.cart.shopping.global.GlobalData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController
{
    @Autowired
    private ProductService productService;

    @GetMapping({"","/home"})
    public String homePage(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable Long id,Model model)
    {
        model.addAttribute("product", productService.getProduct(id));
        return "viewProduct";
    }
}
