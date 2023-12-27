package com.vivek.commerce.product.category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String categoryName);
    CategoryDTO saveCategoryWithParent(CategoryDTO currentCategoryDTO);
    CategoryDTO updateCategory(String categoryName, CategoryDTO categoryDTO);
    void deleteCategory(String categoryName);
}