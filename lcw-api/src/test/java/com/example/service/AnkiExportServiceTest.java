package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.example.domain.Card;

@TestInstance(Lifecycle.PER_CLASS)
class AnkiExportServiceTest {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get("collection.anki2"));
	}
	
	@AfterAll
	void tearDown() throws IOException {
		Files.deleteIfExists(Paths.get("collection.anki2"));
	}

	@Test
	void outputZipFileShouldContainDbFileAndMediaFile() throws Exception {
		List<Card> cards = Arrays.asList(new Card("first", "pierwsze", "the first sentence"),
				new Card("second", "drugie", "the second sentence"));
		AnkiExportService service = new AnkiExportService();
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		service.exportToApkg(output, cards);
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(output.toByteArray()));
		
		ZipEntry entry = zipInputStream.getNextEntry();
		assertThat(entry.getName()).isEqualTo("collection.anki2");
		
		entry= zipInputStream.getNextEntry();
		assertThat(entry.getName()).isEqualTo("media");
	}

}
