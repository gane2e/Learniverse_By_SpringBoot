package com.lms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.entity.Category;
import com.lms.entity.SubCategory;
import com.lms.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories"; // categories.html
    }

    @GetMapping("/subcategories")
    @ResponseBody
    public List<SubCategory> getSubCategories(@RequestParam("categoryId") Long categoryId) {
        List<SubCategory> subCategoryList =  categoryService.getSubCategoriesByCategoryId(categoryId);

        return subCategoryList;
    }



}
