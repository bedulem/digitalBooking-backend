package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Category;
import com.grupo3.digitalBooking.model.CategoryDto;

import java.util.Collection;

public interface ICategoryService {

    void createCategory(Category category);
    Category updateCategory (Category category, CategoryDto categoryDto);
    Collection<Category> readCategories ();
    void deleteCategory(Long id);
}
