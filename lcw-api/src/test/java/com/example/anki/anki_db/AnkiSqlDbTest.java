package com.example.anki.anki_db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(Lifecycle.PER_CLASS)
class AnkiSqlDbTest {
	private AnkiSqlDb sqlDb;

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

	@ParameterizedTest(name = "{index} check if {0} table created")
	@ValueSource(strings = { "cards", "col", "graves", "notes", "revlog" })
	void whenClassCreated_thenDatabaseShouldHaveAllTables(String tableName) throws Exception {
		Connection connection = sqlDb.getDbConnection();

		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet resultSet = metaData.getTables(null, null, tableName, new String[] { "TABLE" });

		assertTrue(resultSet.next());
	}
}