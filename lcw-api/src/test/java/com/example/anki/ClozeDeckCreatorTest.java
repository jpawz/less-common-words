package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.domain.Card;

class ClozeDeckCreatorTest {

    public static Stream<Arguments> provideArguments() {
	return Stream.of(Arguments.of("cloze", "Sentence for {{c1::cloze::translation}} test."), //
		Arguments.of("sentence", "{{c1::Sentence::translation}} for cloze test."), //
		Arguments.of("test", "Sentence for cloze {{c1::test::translation}}.") //
	);
    }

    @ParameterizedTest(name = "{index} check value for {0}")
    @MethodSource("provideArguments")
    void deckCratorShouldProduceProperClozeSentence(String word, String clozeSentence) {
	ClozeDeckCreator creator = new ClozeDeckCreator();
	String sentence = "Sentence for cloze test.";
	String translation = "translation";
	List<Card> cards = Arrays.asList(new Card(word, translation, sentence));

	Deck deck = creator.makeDeck(cards);

	assertThat(deck.get(0).question())
		.contains(Map.entry("sentence", clozeSentence));
    }
}
