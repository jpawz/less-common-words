package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

class DictionaryapiServiceTest {

	private String jsonResponse = """
			  [
			    {
			      "word": "hello",
			      "phonetic": "həˈləʊ",
			      "phonetics": [
			        {
			          "text": "həˈləʊ",
			          "audio": "//ssl.gstatic.com/dictionary/static/sounds/20200429/hello--_gb_1.mp3"
			        },
			        {
			          "text": "hɛˈləʊ"
			        }
			      ],
			      "origin": "early 19th century: variant of earlier hollo ; related to holla.",
			      "meanings": [
			        {
			          "partOfSpeech": "exclamation",
			          "definitions": [
			            {
			              "definition": "used as a greeting or to begin a phone conversation.",
			              "example": "hello there, Katie!",
			              "synonyms": [],
			              "antonyms": []
			            }
			          ]
			        },
			        {
			          "partOfSpeech": "noun",
			          "definitions": [
			            {
			              "definition": "an utterance of ‘hello’; a greeting.",
			              "example": "she was getting polite nods and hellos from people",
			              "synonyms": [],
			              "antonyms": []
			            }
			          ]
			        },
			        {
			          "partOfSpeech": "verb",
			          "definitions": [
			            {
			              "definition": "say or shout ‘hello’.",
			              "example": "I pressed the phone button and helloed",
			              "synonyms": [],
			              "antonyms": []
			            }
			          ]
			        }
			      ]
			    }
			  ]
			""";

	@Test
	void shouldExtractTheFirstDefinition() throws JsonMappingException, JsonProcessingException {
		String definition = "used as a greeting or to begin a phone conversation.";
		DictionaryapiService service = new DictionaryapiService();

		String translation = service.getFirstDefinition(jsonResponse);

		assertThat(translation).isEqualTo(definition);
	}

}
