package com.example.anki;

import java.util.Map;

/**
 * 
 * Class represents an AnkiCard: question and answer fields.
 *
 */
public record AnkiCard(Map<String, String> question, Map<String, String> answer) {

}
