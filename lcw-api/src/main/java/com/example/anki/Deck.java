package com.example.anki;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deck<T extends AnkiCard> extends AbstractList<AnkiCard> {
	private static final int MIN_DECK_SIZE = 2800;
	private final ArrayList<AnkiCard> cards = new ArrayList<>(MIN_DECK_SIZE);
	private final Set<String> sounds = new HashSet<>();
	private String questionTemplate;
	private String answerTemplate;
	private String cssStyle = CardStyles.DEFAULT_STYLE;
	private static Pattern pattern = Pattern.compile(".*\\[sound:(.+?)\\].*");

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

	public Set<String> getSounds() {
		return sounds;
	}

	@Override
	public boolean add(AnkiCard card) {
		addSound(card);
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

	private void addSound(AnkiCard card) {
		card.getQuestion().forEach((k, v) -> setSound(v));
		card.getAnswer().forEach((k, v) -> setSound(v));
	}

	private void setSound(String v) {
		Matcher matcher = pattern.matcher(v);
		if (matcher.matches()) {
			sounds.add(matcher.group(1));
		}
	}
}
