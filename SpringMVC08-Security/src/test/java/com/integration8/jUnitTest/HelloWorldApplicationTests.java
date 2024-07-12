package com.integration8.jUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.integration8.security.model.Product;
import com.integration8.security.service.ProductService;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private InMemoryUserDetailsManager userDetailsManager;

    @Test
    public void testPrincipal() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/index.html"));
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/index.html"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"));
    }

    @Test
    public void testProductos() throws Exception {
        List<Product> productList = new ArrayList<>();
        when(productService.findProducts()).thenReturn(productList);
        mockMvc.perform(get("/user/index.html"))
               .andExpect(status().isOk())
               .andExpect(view().name("user/index"))
               .andExpect(model().attributeExists("productos"));
    }

    @Test
    public void testSharedIndex() throws Exception {
        mockMvc.perform(get("/shared/index.html"))
               .andExpect(status().isOk())
               .andExpect(view().name("shared/index"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login.html"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }

}