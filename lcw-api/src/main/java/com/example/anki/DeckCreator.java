package com.example.anki;

import java.util.List;

import com.example.domain.Card;

/**
 * Interface for classes that creates a {@link Deck} from a list of cards.
 *
 */
public interface DeckCreator {

    /**
     * Make {@link Deck} from list of {@link Card}.
     * @param cards
     * @return
     */
    Deck makeDeck(List<Card> cards);

}
