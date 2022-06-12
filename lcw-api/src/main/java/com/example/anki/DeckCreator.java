package com.example.anki;

import java.util.List;

import com.example.domain.Card;

public interface DeckCreator {

	Deck makeDeck(List<Card> cards);

}
