package com.vivek.commerce.product;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.vivek.commerce.product.category.Category;
import com.vivek.commerce.product.category.CategoryDTO;
import com.vivek.commerce.product.category.CategoryRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class CommerceProductService implements  ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::convertToDto);
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        Product product = getProductOrThrow(productId);
        return convertToDto(product);
    }

    @Override
    public ProductResponseDTO createProduct(@Validated @NotNull ProductRequestDTO productRequestDTO) {
        Product newProduct = convertToEntity(productRequestDTO);
        String categoryName = productRequestDTO.getCategory().getName();
        Category category = categoryRepository.findById(categoryName).orElseThrow(() -> new ProductServiceException("No category found to map product"));
        newProduct.setCategory(category);
        Product savedProduct = productRepository.save(newProduct);
        return convertToDto(savedProduct);
    }

    //TODO -> need to test working (in progress)    
    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product existingProduct = getProductOrThrow(productId);

        // Update the existing product with the new data from productRequestDTO
        existingProduct.setTitle(productRequestDTO.getTitle());
        existingProduct.setDescription(productRequestDTO.getDescription());
        // Update other fields as needed

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProductOrThrow(productId);
        // Delete embedded collection elements
        product.getSizes().clear(); 
        productRepository.delete(product);
    }

    private Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    private ProductResponseDTO convertToDto(Product product) {
        ProductResponseDTO response =  modelMapper.map(product, ProductResponseDTO.class);
        response.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));        
        return response;
    }

    private Product convertToEntity(ProductRequestDTO productRequestDTO) {
        return  modelMapper.map(productRequestDTO, Product.class);
    }
}
