package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Card;
import com.example.service.AnkiExportService;
import com.example.service.CsvExportService;

@CrossOrigin
@RestController
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private CsvExportService csvService;

	@Autowired
	private AnkiExportService ankiService;

	@PostMapping(value = "/csv", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void exportCSV(HttpServletResponse servletResponse, @RequestBody List<Card> cards) throws IOException {
		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"cards.csv\"");
		csvService.writeToCsv(servletResponse.getWriter(), cards);
	}

	@PostMapping(value = "/anki", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void exportAnki(HttpServletResponse servletResponse, @RequestBody List<Card> cards) throws Exception {
		servletResponse.setContentType("application/octet-stream");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"cards.apkg\"");
		ankiService.exportToApkg(servletResponse.getOutputStream(), cards);
	}

}
