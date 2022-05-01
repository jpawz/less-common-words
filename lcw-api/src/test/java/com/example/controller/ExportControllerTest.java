package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.domain.Card;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ExportControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void givenCards_whenPostExportCsv_thenReturnCSV() throws Exception {
		List<Card> cards = Arrays.asList(new Card("word", "translation", "word in sentence"));

		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/export/csv", cards, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("word,translation,word in sentence");
	}

}
