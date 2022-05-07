package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.WordRank;
import com.example.service.WordsRankService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class WordRankController {

	@Autowired
	private WordsRankService service;

	@GetMapping
	public WordRank getWordRank(@RequestParam String word) {
		return service.getWordRankForWord(word);
	}

	@PostMapping
	public WordRank saveWordRank(@RequestParam String word, @RequestParam int rank) {
		return service.saveWordsRank(new WordRank(word, rank));
	}

	@PostMapping("words")
	public List<WordRank> getWordRanks(@RequestBody Set<String> words) {
		return service.getWordsRankForWords(words);
	}
}
