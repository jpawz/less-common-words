package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.WordRank;
import com.example.service.WordsRankService;

@RestController
@RequestMapping("api")
public class WordRankController {

	@Autowired
	WordsRankService service;

	@CrossOrigin
	@GetMapping
	public WordRank getWordRankForText(@RequestParam String word) {
		return service.getWordRankForWord(word);
	}

	@PostMapping
	public WordRank saveWordRank(@RequestParam String word, @RequestParam int rank) {
		return service.saveWordsRank(new WordRank(word, rank));
	}
}
