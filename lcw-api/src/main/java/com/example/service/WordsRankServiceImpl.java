package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@Service
public class WordsRankServiceImpl implements WordsRankService {

	private final WordRankRepository wordRankRepository;

	@Autowired
	public WordsRankServiceImpl(WordRankRepository repository) {
		this.wordRankRepository = repository;
	}

	@Override
	public List<WordRank> getWordsRankForText(String text) {

		String splitRegEx = "\\W+";
		List<String> words = Arrays.asList(text.split(splitRegEx));
		List<WordRank> wordsRank = wordRankRepository.findByWordIn(words);

		words.forEach(s -> {
			WordRank wordRank = new WordRank();
			wordRank.setWord(s);
			if (!wordsRank.contains(wordRank)) {
				wordRank.setRank(WordRank.RANK_NEVER_IGNORE);
				wordsRank.add(wordRank);
			}
		});

		return wordsRank;
	}

	@Override
	public void saveWordsRank(List<WordRank> wordsRank) {
	}

}
