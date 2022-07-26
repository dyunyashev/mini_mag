package com.web_shop.controllers;

import com.web_shop.entites.Product;
import com.web_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

//    // перенести работу с репозиторием пользователей в соответствующий сервис
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    public void setTestProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsList(Principal principal, Model model,
                                   @RequestParam(value = "word", required = false) String word,
                                   @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                   @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                   @RequestParam(value = "page", required = false) Integer page) {

//        // информация о текущем пользователе, который прошел аутентификацию
//        // перенести код в соответствующий сервис
//        if (principal != null) {
//            User user = userRepository.findOneByUserName(principal.getName());
//            System.out.println(user.getEmail());
//        }
        if (principal != null) {
            model.addAttribute("userName", principal.getName());
        }
        model.addAttribute("products", productService.getFilter(word, minPrice, maxPrice, page));
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("top3List", productService.getTop3List());

        return "products";
    }

    @GetMapping("/add")
    @Secured(value = "ROLE_ADMIN")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }

    @GetMapping("/edit/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.readById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    @Secured(value = "ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    //HttpServletRequest request
    //HttpServletResponse response
    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.readById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/remove/{id}")
    public String removeProductById(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return "redirect:/products";
    }
}
