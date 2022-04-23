package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TranslationControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String url;

	@BeforeEach
	public void setup() {
		this.url = String.format("http://localhost:%d/translate", port);
	}

	@Test
	void whenRequestWord_thenReturnTranslations() {
		String word = "house";

		String translation = restTemplate.getForObject(url + "?word=" + word, String.class);

		assertThat(translation).contains("structure");
	}

	@Test
	void whenTranslationNotFound_then404() {
		String word = "itsnotaword";

		ResponseEntity<String> response = restTemplate.getForEntity(url + "?word=" + word, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
