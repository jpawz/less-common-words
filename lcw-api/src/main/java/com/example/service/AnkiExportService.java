package com.example.service;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.example.anki.AnkiApkg;
import com.example.anki.Deck;
import com.example.anki.anki_db.AnkiSqlDb;

@Service
public class AnkiExportService {

    public void exportToApkg(OutputStream outputStream, Deck deck) throws Exception {
	Path databaseName = Files.createTempFile("collection", "anki2");

	try (AnkiSqlDb sqlDb = new AnkiSqlDb(databaseName.toString()); AnkiApkg ankiApkg = new AnkiApkg(outputStream)) {
	    sqlDb.addDeck(deck);

	    byte[] db = sqlDb.getFile();

	    ankiApkg.addToArchive("collection.anki2", db);
	    ankiApkg.addToArchive("media", "{ }".getBytes());
	} finally {
	    Files.deleteIfExists(databaseName);
	}
    }
}
