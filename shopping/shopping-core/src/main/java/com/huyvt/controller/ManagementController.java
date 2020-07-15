package com.huyvt.controller;

import com.huyvt.dao.CategoryDAO;
import com.huyvt.dao.ProductDAO;
import com.huyvt.dto.Category;
import com.huyvt.dto.Product;
import com.huyvt.util.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/manage")
public class ManagementController {
    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false)String operation){
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("title", "Manage Products");

        Product nProduct = new Product();
        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        modelAndView.addObject("product", nProduct);
        if(operation != null){
            if(operation.equals("product")){
                modelAndView.addObject("message", "Product submit successfully");
            }
        }
        return modelAndView;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories(){
        return categoryDAO.list();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, HttpServletRequest request){
        //check if there are any errors
        if(result.hasErrors()){
            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation failed for Product Submission");
            return "page";
        }
        logger.info(product.toString());
        productDAO.add(product);
        if(!product.getFile().getOriginalFilename().equals("")){
            FileUpload.uploadFile(request, product.getFile(), product.getCode());
        }
        return "redirect:/manage/products?operation=product";
    }
}
