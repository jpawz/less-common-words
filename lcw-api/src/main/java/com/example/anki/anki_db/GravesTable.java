package com.example.anki.anki_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GravesTable {
	private static final String TABLE_STATEMENT = "CREATE TABLE graves (" + "usn integer not null,"
			+ "oid integer not null," + "type integer not null);";

	public void setUpTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(TABLE_STATEMENT)) {
			statement.executeUpdate();
		}
	}
}
