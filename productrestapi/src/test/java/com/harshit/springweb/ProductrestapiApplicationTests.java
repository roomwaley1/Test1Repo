package com.harshit.springweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.harshit.springweb.entities.Product;

@SpringBootTest
class ProductrestapiApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	void testGetProduct() {
		System.out.println(baseURL);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "1", Product.class);
		assertNotNull(product);
		assertEquals("Iphone", product.getName());
	}

	@Test
	void testCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Samsung");
		product.setDescription("Its good");
		product.setPrice(2000);
		Product newProduct = restTemplate.postForObject(baseURL, product, Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung", newProduct.getName());
	}

	@Test
	void testUpdate() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "1", Product.class);
		product.setPrice(1450);
		restTemplate.put(baseURL, product);
	}

}
