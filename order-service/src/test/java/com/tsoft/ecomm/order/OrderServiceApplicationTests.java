package com.tsoft.ecomm.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;
import stub.InventoryClientStub;

import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0")
			.withDatabaseName("orders")
			.withUsername("root")
			.withPassword("password");

	@LocalServerPort
	Integer port;


	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		mySQLContainer.start();
	}

	@Test
	void shouldCreateAnOrder() {
		// given
		String orderRequest = """
				{
					"skuCode": "SKU-005",
					"price": 100.00,
					"quantity": 10
				}
				""";
		InventoryClientStub.stubInventoryCall("SKU-005");

		// when
		RestAssured.given()
				.contentType("application/json")
				.body(orderRequest)
				.when()
				.post("/v1/api/order/create")
				.then()
				.statusCode(201)
				.body("orderNumber", notNullValue())
				.body("skuCode", Matchers.equalTo("SKU-005"));
	}

}
