package com.services;

import com.dtos.CreateCategoryDto;
import com.dtos.UpdateCategoryDto;
import com.entities.Category;
import com.entities.Ticket;
import com.repositories.CategoriesRepository;
import com.repositories.TicketsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoryRepository;
    private final TicketsRepository ticketRepository;

    public CategoriesService(CategoriesRepository categoryRepository, TicketsRepository ticketRepository) {
        this.categoryRepository = categoryRepository;
        this.ticketRepository = ticketRepository;
    }

    public Category createCategory(CreateCategoryDto newCategory){
        Category category = new Category(newCategory.name(), newCategory.description());
        return categoryRepository.save(category);
    }

    public void updateCategory(UpdateCategoryDto categoryDto){
        Category category = categoryRepository.getReferenceById(categoryDto.categoryId());
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
    }

    public void deleteCategory(long categoryId){
        if(categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        }
    }

    public List<Ticket> listTicketsByCategory(long categoryId){
        return ticketRepository.findByCategoryId(categoryId);
    }
}
