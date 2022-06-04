package com.example.anki.anki_db;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.anki.AnkiCard;

public class NotesTable {
	private static final String TABLE_STATMENT = "CREATE TABLE notes (" + "id integer primary key,"
			+ "guid text not null," + "mid integer not null," + "mod integer not null," + "usn integer not null,"
			+ "tags text not null," + "flds text not null," + "sfld integer not null," // strange that sfld is integer
																						// not text
			+ "csum integer not null," + "flags integer not null," + "data text not null);";

	private final long mid;
	private final long mod;
	private Set<String> keysSet;

	private static final String QA_SEPARATOR = "" + (char) 0x1F;
	private PreparedStatement notesStatement;

	public NotesTable(long mid, long mod) {
		this.mid = mid;
		this.mod = mod;
	}

	public void setUpTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(TABLE_STATMENT)) {
			statement.executeUpdate();
			notesStatement = connection
					.prepareStatement("INSERT INTO notes VALUES (?, ?, ?, ?, -1, \"\", ?, ?, ?, 0, \"\")");
		}
	}

	public void setKeys(Set<String> keys) {
		this.keysSet = keys;
	}

	public void insertDataIntoBatch(AnkiCard card, long nid) throws SQLException {
		notesStatement.setLong(1, nid);
		notesStatement.setString(2, guid());
		notesStatement.setLong(3, mid);
		notesStatement.setLong(4, mod);
		notesStatement.setString(5, flds(card));
		notesStatement.setString(6, sfld(card));
		notesStatement.setLong(7, csum(sfld(card)));
		notesStatement.addBatch();
	}

	public void executeBatch() throws SQLException {
		notesStatement.executeBatch();
	}

	private String sfld(AnkiCard card) {
		StringJoiner sfld = new StringJoiner(QA_SEPARATOR);
		card.question().forEach((k, v) -> sfld.add(v));
		return sfld.toString();
	}

	private String flds(AnkiCard card) {
		StringJoiner flds = new StringJoiner(QA_SEPARATOR);
		keysSet.forEach((var k) -> {
			if (card.question().containsKey(k)) {
				flds.add(card.question().get(k));
			}
			if (card.answer().containsKey(k)) {
				flds.add(card.answer().get(k));
			}
		});

		return flds.toString();
	}

	private String guid() {
		Random random = new Random();
		String randomString = random.ints(40, 122).mapToObj(i -> (char) i).limit(10)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		return randomString;
	}

	private long csum(String question) {
		String password = DigestUtils.sha1Hex(question);
		BigInteger bi = new BigInteger(password.substring(0, 8), 16);
		return bi.longValue();
	}
}
