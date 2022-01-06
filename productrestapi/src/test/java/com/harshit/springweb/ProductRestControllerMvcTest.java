package com.harshit.springweb;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.harshit.springweb.entities.Product;
import com.harshit.springweb.repos.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRestControllerMvcTest {

	private static final String PRODUCTRESTAPI_URL = "/productrestapi/products/";

	private static final String CONTEXT_PATH = "/productrestapi";

	private static final int PRODUCT_PRICE = 1000;

	private static final String PRODUCT_DESCRIPTION = "GOod";

	private static final String PRODUCT_NAME = "Phone";

	private static final int PRODUCT_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository repository;

	@Test
	void testFindAll() throws Exception {
		Product product = buildProduct();
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(get(PRODUCTRESTAPI_URL).contextPath(CONTEXT_PATH)).andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(products)));
	}
	
	@Test
	public void testCreateProduct() throws JsonProcessingException, Exception {
		Product product = buildProduct();
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(post(PRODUCTRESTAPI_URL).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(product)));
		
	}
	
	@Test
	public void testUpdateProduct() throws JsonProcessingException, Exception {
		Product product = buildProduct();
		product.setPrice(2000);
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(put(PRODUCTRESTAPI_URL).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(product)));
		
	}
	
	@Test
	public void testDelete() throws Exception {
		doNothing().when(repository).deleteById(PRODUCT_ID);
		mockMvc.perform(delete(PRODUCTRESTAPI_URL+PRODUCT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetProduct() throws Exception {
		Product product = buildProduct();
		List<Product> products = Arrays.asList(product);
		when(repository.findById(PRODUCT_ID).get()).thenReturn((Product) products);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(get(PRODUCTRESTAPI_URL+PRODUCT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk());
	}

	private Product buildProduct() {
		Product product = new Product();
		product.setId(PRODUCT_ID);
		product.setName(PRODUCT_NAME);
		product.setDescription(PRODUCT_DESCRIPTION);
		product.setPrice(PRODUCT_PRICE);
		return product;
	}


}
