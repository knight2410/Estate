package com.huyvt.controller;

import com.huyvt.dao.ProductDAO;
import com.huyvt.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProduct() {
        return productDAO.list();
    }

    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductByCategory(@PathVariable int id) {
        return productDAO.listActiveProductsByCategory(id);
    }
}
