package com.example.anki;

import java.util.AbstractList;
import java.util.ArrayList;

public class Deck<T extends AnkiCard> extends AbstractList<AnkiCard> {
	private final ArrayList<AnkiCard> cards = new ArrayList<>();
	private String questionTemplate;
	private String answerTemplate;
	private String cssStyle = CardStyles.DEFAULT_STYLE;

	public String getAnswerTemplate() {
		return answerTemplate;
	}

	public void setAnswerTemplate(String answerTemplate) {
		this.answerTemplate = answerTemplate;
	}

	public String getQuestionTemplate() {
		return questionTemplate;
	}

	public void setQuestionTemplate(String questionTemplate) {
		this.questionTemplate = questionTemplate;
	}

	public void setStyle(String style) {
		this.cssStyle = style;
	}

	public String getStyle() {
		return cssStyle;
	}

	@Override
	public boolean add(AnkiCard card) {
		return cards.add(card);
	}

	@Override
	public AnkiCard get(int index) {
		return cards.get(index);
	}

	@Override
	public int size() {
		return cards.size();
	}

}
