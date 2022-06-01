package com.example.anki.anki_db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.anki.AnkiCard;
import com.example.anki.Deck;

public class AnkiSqlDb {

	private Connection dbConnection;

	private final long mid = System.currentTimeMillis();
	private final long did = System.currentTimeMillis();
	private final long mod = System.currentTimeMillis() / 1000;

	private final CardsTable cardsTable;
	private final ColTable colTable;
	private final GravesTable gravesTable;
	private final NotesTable notesTable;
	private final RevlogTable revlogTable;

	/**
	 * Default constructor. Prepare tables for database.
	 */
	public AnkiSqlDb() {
		cardsTable = new CardsTable(did, mod);
		colTable = new ColTable(mid, did, mod);
		gravesTable = new GravesTable();
		notesTable = new NotesTable(mid, mod);
		revlogTable = new RevlogTable();
	}

	/**
	 * Makes sqlite database.
	 * 
	 * @param deck - deck of cards and templates.
	 */
	public void createDb(Deck<AnkiCard> deck) {
		try {
			dbConnection = DriverManager.getConnection("jdbc:sqlite:collection.anki2");
		} catch (SQLException exception) {
			throw new RuntimeException("Can't initialize database.");
		}
		prepareTables();

		try {
			if (deck.isEmpty()) {
				throw new RuntimeException("Deck doesn't contain any cards.");
			}
			colTable.insertData(deck.getQuestionTemplate(), deck.getAnswerTemplate(), deck.getStyle());
			notesTable.setKeys(colTable.getKeys());
		} catch (SQLException e1) {
			throw new RuntimeException("Can't prepare colTable.");
		}

		long nid = System.currentTimeMillis();
		long id = System.currentTimeMillis();
		for (AnkiCard card : deck) {
			try {
				notesTable.insertDataIntoBatch(card, nid);
				cardsTable.insertDataIntoBatch(id++, nid++);
			} catch (SQLException exception) {
				System.out.println("Card: [" + card + "] wasn't added to database.");
			}
		}

		try {
			notesTable.executeBatch();
			cardsTable.executeBatch();

			dbConnection.commit();
			dbConnection.close();
		} catch (SQLException exception) {
			throw new RuntimeException("Error on executing batch queries: " + exception.getMessage());
		}
	}

	/**
	 * Reads all bytes from collection.anki2 file.
	 *
	 * @return content of collection.anki2 as byte array
	 * @throws IOException when IO error occurs.
	 */
	public byte[] getFile() {
		try {
			return Files.readAllBytes(Paths.get("collection.anki2"));
		} catch (IOException exception) {
			throw new RuntimeException("Can't read file collection.adnki2: " + exception.getMessage());
		}
	}

	private void prepareTables() {
		try {
			cardsTable.setUpTable(dbConnection);
			colTable.setUpTable(dbConnection);
			gravesTable.setUpTable(dbConnection);
			notesTable.setUpTable(dbConnection);
			revlogTable.setUpTable(dbConnection);

			dbConnection.prepareStatement("CREATE INDEX ix_cards_nid on cards (nid);"
					+ "CREATE INDEX ix_cards_sched on cards (did, queue, due);"
					+ "CREATE INDEX ix_cards_usn on cards (usn);" + "CREATE INDEX ix_notes_csum on notes (csum);"
					+ "CREATE INDEX ix_notes_usn on notes (usn);" + "CREATE INDEX ix_revlog_cid on revlog (cid);"
					+ "CREATE INDEX ix_revlog_usn on revlog (usn);").executeUpdate();
		} catch (SQLException exception) {
			throw new RuntimeException("Error on preparation db tables: " + exception.getMessage());
		}
	}

}
