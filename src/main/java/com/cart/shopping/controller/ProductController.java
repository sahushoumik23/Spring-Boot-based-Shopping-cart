package com.cart.shopping.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.cart.shopping.dto.ProductDTO;
import com.cart.shopping.model.Product;
import com.cart.shopping.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController
{
    public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private ProductService pService;

    @GetMapping("/product")
    public String product(Model model)
    {
        model.addAttribute("products",pService.getAllProduct());
        return "products";
    }

    @GetMapping("/product/add")
    public String productAdd(Model model)
    {
        model.addAttribute("productDTO",new ProductDTO());
        return "productsAdd";
    }
    @PostMapping("/product/add")
    public String productAdd(@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam ("productImage") MultipartFile file,@RequestParam ("imgName") String imgName) throws IOException
    {
        String imageUUID;
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        if(!file.isEmpty())
        {
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }
        else
        {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        pService.addProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        pService.removeProduct(id);
        return "redirect:/product";
    }
    
    @GetMapping("/product/update/{id}")
    public String updateProduct(@PathVariable Long id,Model model)
    {
        Product product=pService.getProduct(id);
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }
}
