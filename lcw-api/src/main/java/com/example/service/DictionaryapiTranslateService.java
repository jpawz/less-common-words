package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DictionaryapiTranslateService implements TranslateService {

	private String url = "https://api.dictionaryapi.dev/api/v2/entries/en/";

	@Override
	public String getTranslation(String word) {
		RestTemplate restTemplate = new RestTemplate();

		String json = restTemplate.getForObject(url + word, String.class);
		
		String translation;
		try {
			translation = getFirstDefinition(json);
		} catch (JsonProcessingException e) {
			translation = "";
			e.printStackTrace();
		}

		return translation;
	}

	protected String getFirstDefinition(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(json);

		String definition = node.path(0).path("meanings").path(0).path("definitions").path(0).path("definition").asText();

		return definition;
	}

}
