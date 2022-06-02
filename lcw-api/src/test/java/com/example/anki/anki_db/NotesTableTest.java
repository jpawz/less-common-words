package com.example.anki.anki_db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.anki.AnkiCard;

@TestInstance(Lifecycle.PER_CLASS)
public class NotesTableTest {

	NotesTable notesTable;
	static final long MID = 4564564564564L; // any long number
	static final long NID = 7897897897897L; // any long number
	static final long MOD = 1234567890123L; // any long number
	ResultSet resultSet;
	AnkiCard card;

	@BeforeAll
	public void setUp() throws SQLException {

		Map<String, String> question = new HashMap<>();
		question.put("front", "Lorem ipsum...");
		Map<String, String> answer = new HashMap<>();
		answer.put("back", "...dolor sit amet");
		card = new AnkiCard(question, answer);
		LinkedHashSet<String> keys = new LinkedHashSet<>(Arrays.asList("front", "back"));

		notesTable = new NotesTable(MID, MOD);
		Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		notesTable.setUpTable(connection);
		notesTable.setKeys(keys);
		notesTable.insertDataIntoBatch(card, NID);
		notesTable.executeBatch();
		Statement statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from notes");
	}

	private static Stream<Arguments> provideArguments() {
		return Stream.of(Arguments.of("id", Long.toString(NID)), //
				Arguments.of("mid", Long.toString(MID)), //
				Arguments.of("mod", Long.toString(MOD)), //
				Arguments.of("flds", "Lorem ipsum..." + (char) 0x1F + "...dolor sit amet"), //
				Arguments.of("sfld", "Lorem ipsum..."), //
				Arguments.of("csum", "4036353563") //
		);
	}

	@ParameterizedTest(name = "{index}: check value for {0}")
	@MethodSource("provideArguments")
	public void checkIfTableProperlyFilledWithValues(String valName, String expected) throws Exception {
		assertThat(resultSet.getString(valName)).isEqualTo(expected);
	}

}
