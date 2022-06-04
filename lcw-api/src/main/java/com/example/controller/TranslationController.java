package com.example.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.example.service.TranslateService;

@CrossOrigin
@RestController
@RequestMapping("translate")
public class TranslationController {

	@Autowired
	private TranslateService service;

	@GetMapping
	public String getTranslation(@RequestParam String word, HttpServletResponse response) {
		try {
			return service.getTranslation(word);
		} catch (HttpClientErrorException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation not found", exception);
		}
	}

}
