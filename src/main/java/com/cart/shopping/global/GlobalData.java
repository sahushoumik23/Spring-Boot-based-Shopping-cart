package com.cart.shopping.global;

import java.util.ArrayList;
import java.util.List;

import com.cart.shopping.model.Product;

public class GlobalData
{
    public static List<Product> cart;
    static
    {
        cart = new ArrayList<>();
        
    }
}
