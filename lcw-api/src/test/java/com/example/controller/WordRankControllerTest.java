package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.example.domain.WordRank;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class WordRankControllerTest {

	@LocalServerPort
	private int port;

	private String url;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		url = String.format("http://localhost:%d/api", port);
	}

	@Test
	void whenGetRequestForExistingWord_thenReturnRank() {
		String existingWord = "and";

		WordRank wordRank = this.restTemplate.getForObject(url + "?word=" + existingWord, WordRank.class);

		assertThat(wordRank.getRank()).isEqualTo(3);
	}

	@Test
	void whenPostNewWord_thenReturnWordRank() {
		String newWord = "undercarriage";
		int newRank = 99999;

		WordRank wordRank = this.restTemplate.postForObject(url + "?word=" + newWord + "&rank=" + newRank,
				new WordRank(newWord, newRank), WordRank.class);

		assertThat(wordRank.getRank()).isEqualTo(newRank);
	}

}
