package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void deckShouldHaveDefaultStyle() {
		Deck deck = new Deck();

		String template = deck.getStyle();

		assertThat(template).isNotEmpty();
	}

}
