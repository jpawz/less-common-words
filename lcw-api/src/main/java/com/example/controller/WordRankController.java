package com.example.controller;

import java.util.List;
import java.util.Optional;
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
import com.example.repository.WordRankRepository;

@CrossOrigin
@RestController
@RequestMapping("api")
public class WordRankController {

    @Autowired
    private WordRankRepository repository;

    @GetMapping
    public WordRank getWordRank(@RequestParam String word) {
	return repository.findByWord(word);
    }

    @PostMapping
    public WordRank saveWordRank(@RequestParam String word, @RequestParam int rank) {
	return repository.save(new WordRank(word, rank));
    }

    @PostMapping("words")
    public List<WordRank> getWordRanks(@RequestBody Set<String> words, @RequestParam Optional<Integer> limit) {
	if (limit.isEmpty())
	    return repository.findByWordIn(words);
	else
	    return repository.findByWordInAndRankGreaterThan(words, limit.get());
    }

}
