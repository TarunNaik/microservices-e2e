package com.tsoft.ecomm.inventory;

import com.tsoft.ecomm.inventory.dto.InventoryRequest;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestBody;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@Autowired
	MySQLContainer mySQLContainer;

	@LocalServerPort
	Integer port;

	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		mySQLContainer.start();
	}

	@Test
	void testAvailableQuantityPerSKU() {
		// Given
		String requestBody = """
				{
					"skuCode": "SKU-1",
					"quantity": 10
				}
				""";
		// When
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.post("/v1/api/inventory/create")
				// Then
				.then()
				.statusCode(201)
				.body((Matcher) org.hamcrest.Matchers.notNullValue())
				.body("skuCode", (Matcher) org.hamcrest.Matchers.is("SKU-1"));


	}

}
