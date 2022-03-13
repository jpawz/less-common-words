package com.example.service;

import java.util.Map;

public interface WordsRankService {

	public Map<String, Integer> getWordsRank(String text, int limit);
	
	public void setWordsRank(Map<String, Integer> wordsRank);
}
