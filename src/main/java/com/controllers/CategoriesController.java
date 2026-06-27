package com.controllers;

import com.dtos.CreateCategoryDto;
import com.dtos.UpdateCategoryDto;
import com.entities.Category;
import com.entities.Ticket;
import com.services.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDto category){
        Category newCategory = categoriesService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PatchMapping
    public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryDto category){
        categoriesService.updateCategory(category);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoriesService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listTicketsByCategory(@PathVariable Long id){
        List<Ticket> ticketList = categoriesService.listTicketsByCategory(id);
        return ResponseEntity.ok().body(ticketList);
    }
}
