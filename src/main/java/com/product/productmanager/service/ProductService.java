package com.product.productmanager.service;

import com.product.productmanager.binarytree.CategoryTreeService;
import com.product.productmanager.entities.Category;
import com.product.productmanager.entities.Product;
import com.product.productmanager.exceptions.ProductAlreadyExistsException;
import com.product.productmanager.exceptions.ProductNotFoundException;
import com.product.productmanager.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryTreeService categoryTreeService;

//    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, CategoryTreeService categoryTreeService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryTreeService = categoryTreeService;
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Page<Product> findPaginatedProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public void saveProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ProductAlreadyExistsException(product.getName());
        }
        if (product.getCategory() != null) {
            //Category category = categoryService.findCategoryById(product.getCategory().getId());
            Category category = categoryTreeService.findCategory(product.getCategory().getName());
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    @Transactional
    public Product deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
        return product;
    }

    @Transactional
    public void updateProduct(String id, Product product) {
        Product productFound = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        productFound.setQuantity(product.getQuantity());
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryService.findCategoryById(product.getCategory().getId());
            if (category != null) {
                productFound.setCategory(category);
            }
        }

        productRepository.save(productFound);
    }

/*    @Transactional
    public void updateProduct(String id, Product product) {
        Product productFound = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        productFound.setQuantity(product.getQuantity());
        if (product.getCategory() != null) {
            Category category = categoryTreeService.findCategory(product.getCategory().getName());
            productFound.setCategory(category);
        }
        productRepository.save(productFound);
    }*/

}
