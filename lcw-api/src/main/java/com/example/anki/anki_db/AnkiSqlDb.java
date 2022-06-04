package com.example.anki.anki_db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.anki.AnkiCard;
import com.example.anki.Deck;

/**
 * Represents SQLite database with Anki2 schema.
 *
 */
public class AnkiSqlDb implements AutoCloseable {

	private Connection dbConnection;
	private String dbFile;

	private final long mid = System.currentTimeMillis();
	private final long did = System.currentTimeMillis();
	private final long mod = System.currentTimeMillis() / 1000;

	private final CardsTable cardsTable;
	private final ColTable colTable;
	private final GravesTable gravesTable;
	private final NotesTable notesTable;
	private final RevlogTable revlogTable;

	/**
	 * Default constructor. Prepare tables for database and establish connection.
	 */
	public AnkiSqlDb(String dbFile) {
		this.dbFile = dbFile;
		cardsTable = new CardsTable(did, mod);
		colTable = new ColTable(mid, did, mod);
		gravesTable = new GravesTable();
		notesTable = new NotesTable(mid, mod);
		revlogTable = new RevlogTable();
		try {
			Files.deleteIfExists(Paths.get(dbFile));
			dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			dbConnection.setAutoCommit(false);
		} catch (SQLException | IOException exception) {
			throw new RuntimeException("Can't initialize database.");
		}
		prepareTables();
	}

	/**
	 * Add data to database.
	 * 
	 * @param deck - deck of cards and templates.
	 * @throws SQLException
	 */
	public void addDeck(Deck deck) throws SQLException {

		if (deck.isEmpty()) {
			return;
		}
		colTable.insertData(deck.getQuestionTemplate(), deck.getAnswerTemplate(), deck.getStyle());
		notesTable.setKeys(colTable.getKeys());

		long nid = System.currentTimeMillis();
		long id = System.currentTimeMillis();
		for (AnkiCard card : deck) {

			notesTable.insertDataIntoBatch(card, nid);
			cardsTable.insertDataIntoBatch(id++, nid++);
		}

		notesTable.executeBatch();
		cardsTable.executeBatch();

		dbConnection.commit();

	}

	/**
	 * Reads all bytes from collection.anki2 file.
	 *
	 * @return content of collection.anki2 as byte array
	 * @throws IOException when IO error occurs.
	 */
	public byte[] getFile() {
		try {
			return Files.readAllBytes(Paths.get(this.dbFile));
		} catch (IOException exception) {
			throw new RuntimeException("Can't read file " + this.dbFile + exception.getMessage());
		}
	}

	/**
	 * Close database connection.
	 * 
	 * @throws Exception
	 */
	@Override
	public void close() throws Exception {
		dbConnection.close();
	}

	public Connection getDbConnection() {
		return this.dbConnection;
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
