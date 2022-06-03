package com.example.anki.anki_db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(Lifecycle.PER_CLASS)
public class CardsTableTest {

	private CardsTable cardsTable;
	private static long ID = 1231231231231L; // any long number
	private static long DID = 4564564564564L; // any long number
	private static long NID = 7897897897897L; // any long number
	private static long MOD = 1234567890123L; // any long number
	private Connection connection;
	private ResultSet resultSet;

	@BeforeAll
	public void setUp() throws SQLException {
		cardsTable = new CardsTable(DID, MOD);
		connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		cardsTable.setUpTable(connection);
		cardsTable.insertDataIntoBatch(ID, NID);
		cardsTable.executeBatch();
		Statement statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from cards");
	}

	public static Stream<Arguments> provideArguments() {
		return Stream.of(Arguments.of("id", ID), //
				Arguments.of("did", DID), //
				Arguments.of("nid", NID), //
				Arguments.of("mod", MOD)//
		);
	}

	@ParameterizedTest(name = "{index} check value for {0}")
	@MethodSource("provideArguments")
	void checkIfTableProperlyCreated(String valName, long expected) throws Exception {
		assertThat(resultSet.getString(valName)).isEqualTo(Long.toString(expected));
	}
}
