package rma.training.rmatrainingrestapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RmaTrainingRestAppTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("test: /createGreeting")
	void contextLoads() {
		String ENDPOINT = "/createGreeting";
		String request = "{\"name\": \"lallora\", \"email\": \"lallora@mail.ru\"}";
		ResponseEntity<String> actual = this.restTemplate.postForEntity(ENDPOINT, request, String.class);
		log.info("Response: " + actual);
		String expected = "CREATED";
		assertEquals("Is there something wrong!", expected, actual.getBody());
	}

}
