package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Category;
import com.grupo3.digitalBooking.model.CategoryDto;
import com.grupo3.digitalBooking.repository.CategoryRepository;
import com.grupo3.digitalBooking.service.DAO.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, CategoryDto categoryDto) {
         if(categoryDto.getTitle() != null){
             category.setTitle(categoryDto.getTitle());
        }
        if(categoryDto.getDescription() != null){
            category.setDescription(categoryDto.getDescription());
        }
        if(categoryDto.getUrlImage() != null){
            category.setUrlImage(categoryDto.getUrlImage());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Collection<Category> readCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> readCategory (Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        return category;

    }
}
