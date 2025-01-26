package com.tsoft.ecomm.product;

import com.tsoft.ecomm.product.model.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@Autowired
	MongoDBContainer mongoDbContainer;
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		mongoDbContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		String requestBody = "{\n" +
				"  \"name\": \"Product 1\",\n" +
				"  \"description\": \"Product 1 description\",\n" +
				"  \"price\": 100.00\n" +
				"}";
		RestAssured.given()
				.body(requestBody)
				.contentType("application/json")
				.when()
				.post("/v1/api/product/create")
				.then()
				.statusCode(201)
				.body("name", org.hamcrest.Matchers.equalTo("Product 1"));

	}

}
