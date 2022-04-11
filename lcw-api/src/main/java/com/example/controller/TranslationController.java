package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TranslateService;

@CrossOrigin
@RestController
@RequestMapping("translate")
public class TranslationController {

	@Autowired
	private TranslateService service;

	@GetMapping
	public String getTranslation(@RequestParam String word) {
		return service.getTranslation(word);
	}

}
