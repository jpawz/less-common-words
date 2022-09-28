package com.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@CrossOrigin
@RestController
@RequestMapping("ranks")
public class WordRankController {

    @Autowired
    private WordRankRepository repository;

    @GetMapping("/{dataset}")
    public WordRank getWordRank(@PathVariable(required = true) String dataset, @RequestParam String word) {
	return repository.findByWord(dataset, word);
    }

    @GetMapping()
    public Set<String> getDatasetNames() {
	return repository.getDatasetNames();
    }

    @PostMapping("/{dataset}")
    public WordRank saveWordRank(@PathVariable String dataset, @RequestParam String word, @RequestParam int rank) {
	return repository.save(dataset, new WordRank(word, rank));
    }

    @PostMapping("/{dataset}/words")
    public List<WordRank> getWordRanks(@RequestBody Set<String> words, @PathVariable String dataset,
	    @RequestParam Optional<Integer> limit) {
	if (limit.isEmpty())
	    return repository.findByWordIn(dataset, words);
	else
	    return repository.findByWordInAndRankGreaterThan(dataset, words, limit.get());
    }

}
