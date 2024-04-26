package com.example.project.EcommerceApp.global;

import java.util.ArrayList;
import java.util.List;

import com.example.project.EcommerceApp.model.Product;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
