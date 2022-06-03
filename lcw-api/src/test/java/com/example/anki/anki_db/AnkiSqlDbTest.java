package com.example.anki.anki_db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.anki.AnkiCard;
import com.example.anki.Deck;

@TestInstance(Lifecycle.PER_CLASS)
public class AnkiSqlDbTest {
	static Deck<AnkiCard> deck = new Deck<>();
	static AnkiSqlDb sqlDb;
	static Statement statement;

	@BeforeAll
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get("collection.anki2"));
		sqlDb = new AnkiSqlDb();
	}

	@AfterAll
	public void tearDown() throws Exception {
		sqlDb.close();
		Files.deleteIfExists(Paths.get("collection.anki2"));
	}

	public static Stream<Arguments> provideArguments() {
		return Stream.of( //
				Arguments.of("cards"), //
				Arguments.of("col"), //
				Arguments.of("graves"), //
				Arguments.of("notes"), //
				Arguments.of("revlog"));
	}

	@ParameterizedTest(name = "{index} check if {0} table created")
	@MethodSource("provideArguments")
	public void whenClassCreated_thenDatabaseShouldHaveAllTables(String tableName) throws Exception {

		Connection connection = sqlDb.getDbConnection();

		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet resultSet = metaData.getTables(null, null, tableName, new String[] { "TABLE" });
		assertTrue(resultSet.next());
	}
}