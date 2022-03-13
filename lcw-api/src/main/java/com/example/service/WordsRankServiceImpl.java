package com.example.service;

import java.util.Map;

public class WordsRankServiceImpl implements WordsRankService {

	private Map<String, Integer> wordsRank;
	
	@Override
	public Map<String, Integer> getWordsRank(String text, int limit) {
		return wordsRank;
	}

	@Override
	public void setWordsRank(Map<String, Integer> wordsRank) {
		this.wordsRank = wordsRank;
	}
	
	
}
