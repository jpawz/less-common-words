package com.example.service;

import com.example.domain.WordRank;

public interface WordsRankService {
	
	public WordRank getWordRankForWord(String word);
	
	/**
	 * Store a custom words rank values in DB.
	 * @param wordsRank - WordRank objects.
	 * @return newly created WordRank
	 */
	public WordRank saveWordsRank(WordRank wordsRank);
}
