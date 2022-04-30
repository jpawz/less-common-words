package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.domain.Card;

class CsvExportServiceTest {

	@Test
	void shouldProduceProperCsv() throws IOException {
		List<Card> cards = Arrays.asList(new Card("first", "pierwsze", "the first sentence"),
				new Card("second", "drugie", "the second sentence"));
		CsvExportService service = new CsvExportService();

		Writer writer = new StringWriter();
		service.writeToCsv(writer, cards);

		assertThat(writer.toString()).contains("first,pierwsze,the first sentence");
	}

}
