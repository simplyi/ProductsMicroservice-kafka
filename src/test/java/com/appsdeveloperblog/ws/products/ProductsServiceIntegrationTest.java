package com.appsdeveloperblog.ws.products;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.appsdeveloperblog.ws.products.rest.CreateProductRestModel;
import com.appsdeveloperblog.ws.products.service.ProductService;

@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test") // application-test.properties
@EmbeddedKafka(partitions=3, count=3, controlledShutdown=true)
@SpringBootTest(properties="spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class ProductsServiceIntegrationTest {
	
	@Autowired
	private ProductService productService;

	@Test
	void testCreateProduct_whenGivenValidProductDetails_successfullySendsKafkaMessage() throws Exception {
		
		// Arrange 
		
		String title="iPhone 11";
		BigDecimal price = new BigDecimal(600);
		Integer quantity = 1;
		
		CreateProductRestModel createProductRestModel = new CreateProductRestModel();
		createProductRestModel.setPrice(price);
		createProductRestModel.setQuantity(quantity);
		createProductRestModel.setTitle(title);
		
		// Act 
		
		productService.createProduct(createProductRestModel);
		
		
		// Assert
	}
	
}
