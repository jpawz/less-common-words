package com.example.anki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.domain.Card;

/**
 * Create {@link Deck} of <a href="https://docs.ankiweb.net/editing.html#cloze-deletion">cloze-deletion</a> cards.
 *
 */
public class ClozeDeckCreator implements DeckCreator {
    @Override
    public Deck makeDeck(List<Card> cards) {
	Deck deck = new Deck();
	deck.setQuestionTemplate("{{cloze:sentence}}");
	deck.setAnswerTemplate("{{cloze:sentence}}<br>{{translation}}");
	deck.setStyle(CardStyles.DEFAULT_STYLE + CardStyles.DEFAULT_CLOZE_STYLE);

	cards.forEach(card -> deck.add(makeAnkiCard(card)));

	return deck;
    }

    private AnkiCard makeAnkiCard(Card card) {
	Map<String, String> question = new HashMap<>();
	Map<String, String> answer = new HashMap<>();
	String clozeSentence = makeCloze(card.getWord(), card.getSentence(), card.getTranslation());
	question.put("sentence", clozeSentence);
	answer.put("translation", card.getTranslation());
	return new AnkiCard(question, answer);
    }

    private String makeCloze(String word, String sentence, String translation) {
	Matcher matcher = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE).matcher(sentence);
	matcher.find();
	StringBuilder cloze = new StringBuilder();
	cloze.append(sentence.substring(0, matcher.start()))
		.append("{{c1::")
		.append(sentence.substring(matcher.start(), matcher.end()))
		.append("::")
		.append(translation)
		.append("}}")
		.append(sentence.substring(matcher.end()));
	return cloze.toString();
    }
}
