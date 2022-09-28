package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

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
	url = String.format("http://localhost:%d/ranks", port);
    }

    @Test
    void whenGetRequestForExistingWord_thenReturnRank() {
	String existingWord = "and";

	WordRank wordRank = this.restTemplate.getForObject(url + "/google-10000-english-usa/?word=" + existingWord,
		WordRank.class);

	assertThat(wordRank.getRank()).isEqualTo(3);
    }

    @Test
    void whenPostNewWord_thenReturnWordRank() {
	String newWord = "undercarriage";
	int newRank = 99999;

	WordRank wordRank = this.restTemplate.postForObject(
		url + "/google-10000-english-usa/?word=" + newWord + "&rank=" + newRank,
		new WordRank(newWord, newRank), WordRank.class);

	assertThat(wordRank.getRank()).isEqualTo(newRank);
    }

    @Test
    void whenPostRequestForWordsWithLimit_thenReturnRanks() {
	int limit = 40;
	String rank27 = "new";
	String rank41 = "search";
	String rank42 = "free";
	Set<String> words = new HashSet<>(Arrays.asList(rank27, rank41, rank42));

	ResponseEntity<WordRank[]> response = restTemplate.postForEntity(
		url + "/google-10000-english-usa/words?limit=" + limit, words,
		WordRank[].class);

	assertThat(response.getBody()).containsExactlyInAnyOrder(new WordRank(rank41, 41), new WordRank(rank42, 42));
    }

    @Test
    void whenGetRequestForDatasetNames_thenReturnDatasetNames() {

	String[] datesetNames = restTemplate.getForObject(url, String[].class);

	assertThat(datesetNames).contains("google-10000-english-usa");
    }

}
