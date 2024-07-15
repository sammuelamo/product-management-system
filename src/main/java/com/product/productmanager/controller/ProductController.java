package com.product.productmanager.controller;
import com.product.productmanager.entities.Category;
import com.product.productmanager.entities.Product;
import com.product.productmanager.service.CategoryService;
import com.product.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

//    @Autowired
    public ProductController( ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


@GetMapping()
public String getAllProducts(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "name") String sortField,
                             @RequestParam(defaultValue = "asc") String sortDir,
                             @RequestParam(defaultValue = "") String keyword,
                             Model model) {
    Pageable pageable = PageRequest.of(page, 5, Sort.by(sortField).ascending());
    if (sortDir.equals("desc")) {
        pageable = PageRequest.of(page, 5, Sort.by(sortField).descending());
    }

    Page<Product> productPage;
    if (keyword.isEmpty()) {
        productPage = productService.findPaginatedProducts(pageable);
    } else {
        productPage = productService.searchProducts(keyword, pageable);
    }

    model.addAttribute("products", productPage.getContent());
    model.addAttribute("totalPages", productPage.getTotalPages());
    model.addAttribute("currentPage", page);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
    model.addAttribute("keyword", keyword);
    return "products";
}

    @GetMapping("/add")
    public String addProductForm(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "add-product";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        System.out.println("welcome" + product.getCategory().getName());
        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.findProductById(id);
    }

    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable String id, Model model) {
        Product product = productService.findProductById(id);
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "update-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProductById(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
