package com.example.anki.anki_db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RevlogTableTest {

	RevlogTable table;
	Connection connection;

	@BeforeEach
	public void setUp() throws SQLException {
		table = new RevlogTable();
		connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		table.setUpTable(connection);
	}

	@Test
	public void shouldRevlogTableBeCreated() throws SQLException {
		Statement statement = connection.createStatement();
		assertThat(statement.executeUpdate("select * from revlog")).isEqualTo(0);
	}

}
