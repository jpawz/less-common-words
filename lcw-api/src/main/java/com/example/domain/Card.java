package com.example.domain;

public class Card {

	private String word;
	private String translation;
	private String sentence;

	public Card(String word, String translation, String sentence) {
		super();
		this.word = word;
		this.translation = translation;
		this.sentence = sentence;
	}

	public Card() {

	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

}
