package com.cart.shopping.controller;

import com.cart.shopping.global.GlobalData;
import com.cart.shopping.service.ProductService;
import com.cart.shopping.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController
{
    @Autowired
    private ProductService pService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id)
    {
        GlobalData.cart.add(pService.getProduct(id));
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String getCart(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeItem(@PathVariable int index)
    {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model)
    {
        var sum=GlobalData.cart.stream().mapToDouble(Product::getPrice).sum();
        model.addAttribute("total",sum );
        return "checkout";
    }

}
