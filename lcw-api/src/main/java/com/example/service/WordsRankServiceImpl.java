package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.WordRank;

@Service
public class WordsRankServiceImpl implements WordsRankService {

	private List<WordRank> wordsRank;

	@Override
	public List<WordRank> getWordsRankForText(String text) {
		if (wordsRank == null) {
			wordsRank = new ArrayList<>();
		}
		String splitRegEx = "\\W+";
		Arrays.asList(text.split(splitRegEx)).forEach(s -> {
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
	public void setWordsRank(List<WordRank> wordsRank) {
		this.wordsRank = new ArrayList<>(wordsRank);
	}

}
