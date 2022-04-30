package com.example.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import com.example.domain.Card;

@Service
public class CsvExportService {

	public void writeToCsv(Writer writer, List<Card> cards) throws IOException {
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			cards.forEach(card -> {
				try {
					csvPrinter.printRecord(card.getWord(), card.getTranslation(), card.getSentence());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
}
