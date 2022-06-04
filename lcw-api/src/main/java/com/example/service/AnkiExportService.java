package com.example.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.anki.AnkiApkg;
import com.example.anki.AnkiCard;
import com.example.anki.Deck;
import com.example.anki.anki_db.AnkiSqlDb;
import com.example.domain.Card;

@Service
public class AnkiExportService {

	public void exportToApkg(OutputStream outputStream, List<Card> cards) throws Exception {
		Deck deck = makeDeck(cards);

		try (AnkiSqlDb sqlDb = new AnkiSqlDb("collection.anki2"); AnkiApkg ankiApkg = new AnkiApkg(outputStream)) {
			sqlDb.addDeck(deck);

			byte[] db = sqlDb.getFile();

			ankiApkg.addToArchive("collection.anki2", db);
			ankiApkg.addToArchive("media", "{ }".getBytes());
		}
	}

	private Deck makeDeck(List<Card> cards) {
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