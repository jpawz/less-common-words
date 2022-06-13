package com.example.anki;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents collection of {@link AnkiCards}. The class extends
 * {@link AbstractList}. The class contains List of {@link AnkiCards}, templates
 * for question and answer and CSS style.
 */
public class Deck extends AbstractList<AnkiCard> {

    private final ArrayList<AnkiCard> cards = new ArrayList<>();
    private String questionTemplate;
    private String answerTemplate;
    private String cssStyle = CardStyles.DEFAULT_STYLE;

    public String getAnswerTemplate() {
	return answerTemplate;
    }

    /**
     * Set the template for answer side of {@link AnkiCard}. For example:
     * <code><br> {{back}}} </code> or <br>
     * <code> {{cloze:sentence}} </code> (<a href="https://docs.ankiweb.net/editing.html#cloze-deletion">cloze-deletion</a> cards)
     * @param answerTemplate
     */
    public void setAnswerTemplate(String answerTemplate) {
	this.answerTemplate = answerTemplate;
    }

    public String getQuestionTemplate() {
	return questionTemplate;
    }

    /**
     * Set the template for question side of {@link AnkiCard}. For example:
     * <code><br> {{front}}} </code>
     * 
     * @param questionTemplate
     */
    public void setQuestionTemplate(String questionTemplate) {
	this.questionTemplate = questionTemplate;
    }

    /**
     * Set CSS style for this Deck.
     * 
     * @param style - CSS style specifying Deck appearance.
     */
    public void setStyle(String style) {
	this.cssStyle = style;
    }

    /**
     * 
     * @return CSS style specifying Deck appearance.
     */
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(answerTemplate, cards, cssStyle, questionTemplate);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Deck other = (Deck) obj;
	return Objects.equals(answerTemplate, other.answerTemplate) && Objects.equals(cards, other.cards)
		&& Objects.equals(cssStyle, other.cssStyle) && Objects.equals(questionTemplate, other.questionTemplate);
    }

}
