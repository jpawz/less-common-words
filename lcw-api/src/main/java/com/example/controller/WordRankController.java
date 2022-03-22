package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.WordRank;
import com.example.service.WordsRankService;

@RestController
@RequestMapping("api")
public class WordRankController {

	@Autowired
	WordsRankService service;

	@GetMapping
	public List<WordRank> getWordRanksForText(@RequestBody String text) {
		return service.getWordsRankForText(text);
	}

}
