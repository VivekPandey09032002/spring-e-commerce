package com.vivek.commerce.product.category;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommerceCategoryService implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public CategoryDTO saveCategoryWithParent(CategoryDTO currentCategoryDTO) {
		Category currentCategory = convertToEntity(currentCategoryDTO);
		if (!validCategory(currentCategory)) {
			throw new CategoryException("Parent category cannot be null or name cannot be blank");
		}
		
		Category parentCategory = currentCategory.getParentCategory();

		// If the parentCategory has a name, check if it exists in the database
		Optional<Category> optionalParentCategory = categoryRepository.findById(parentCategory.getName());
		
		// exists then get or save in database
		parentCategory = optionalParentCategory.isEmpty() ? categoryRepository.save(parentCategory)
				: optionalParentCategory.get();
		
		// Set the resolved parent category for the current category
		currentCategory.setParentCategory(parentCategory);

		currentCategory = categoryRepository.save(currentCategory);
		
		return modelMapper.map(currentCategory, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(this::convertToDto).toList();
	}

	@Override
	public CategoryDTO getCategoryByName(String categoryName) {
		Category category = getCategoryOrThrow(categoryName);
		return convertToDto(category);
	}


	@Override
	public CategoryDTO updateCategory(String categoryName, CategoryDTO categoryDTO) {
		Category existingCategory = getCategoryOrThrow(categoryName);
		modelMapper.map(categoryDTO, existingCategory);
		Category updatedCategory = categoryRepository.save(existingCategory);
		return convertToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(String categoryName) {
		Category category = getCategoryOrThrow(categoryName);
		categoryRepository.delete(category);
	}

	private boolean validCategory(Category category) {
		return Objects.nonNull(category.getParentCategory()) && !category.getParentCategory().getName().isBlank();
	}

	private Category getCategoryOrThrow(String categoryName) {
		if(categoryName.isBlank()) {
			throw new CategoryException("category name cannot be blank");
		}
		return categoryRepository.findById(categoryName)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + categoryName));
	}

	private CategoryDTO convertToDto(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}

	private Category convertToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
}
