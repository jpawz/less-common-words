package com.example.anki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.domain.Card;

/**
 * Create {@link Deck} of simple Question and Answer type of cards.
 *
 */
public class BasicDeckCreator implements DeckCreator {

    @Override
    public Deck makeDeck(List<Card> cards) {
	Deck deck = new Deck();
	deck.setQuestionTemplate("{{front}}");
	deck.setAnswerTemplate("{{back}}");

	cards.forEach(card -> deck.add(makeAnkiCard(card)));

	return deck;
    }

    private AnkiCard makeAnkiCard(Card card) {
	Map<String, String> question = new HashMap<>();
	Map<String, String> answer = new HashMap<>();
	question.put("front", card.getWord());
	answer.put("back", card.getTranslation());
	return new AnkiCard(question, answer);
    }

}
