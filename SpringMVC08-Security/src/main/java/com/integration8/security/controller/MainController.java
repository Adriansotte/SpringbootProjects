package com.integration8.security.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.unbescape.html.HtmlEscape;

import com.integration8.security.model.Product;
import com.integration8.security.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Application home page and login.
 */
@Controller
public class MainController {
	
	@Autowired
    private InMemoryUserDetailsManager userDetailsManager;
	
	@Autowired
    private ProductService productService;

    @GetMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

    /** Home page. */
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    /** User zone index. */
    @GetMapping("/user/index.html")
    public String showUserProducts(Model model) {
        List<Product> productList = productService.findProducts(); // Obtén la lista de productos desde tu servicio
        model.addAttribute("productos", productList); // Agrega la lista de productos al modelo
        return "user/index"; // Devuelve la vista que muestra la lista de productos
    }

    /** Shared zone index. */
    @GetMapping("/shared/index.html")
    public String sharedIndex() {
        return "shared/index";
    }

    /** Login form. */
    @GetMapping("/login.html")
    public String login() {
        return "login";
    }
    
    @GetMapping("/admin/index.html")
    public String listUsers(Model model) {
    	try {
    		Field field = userDetailsManager.getClass().getDeclaredField("users");
    		field.setAccessible(true);
    		Map<String, UserDetails> usersMap = (Map<String, UserDetails>) field.get(userDetailsManager);
    		List<String> users = usersMap.values().stream()
    				.map(user -> user.getUsername() + "- Roles " + user.getAuthorities().toString())
    				.collect(Collectors.toList());
    		model.addAttribute("users", users);
    	} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
    		e.printStackTrace();
    	}
    	return "admin/index";
    }
    

    /** Login form with error. */
    @GetMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /** Simulation of an exception. */
    @GetMapping("/simulateError.html")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /** Error page. */
    @GetMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Error page. */
    @GetMapping("/403.html")
    public String forbidden() {
        return "403";
    }


}
