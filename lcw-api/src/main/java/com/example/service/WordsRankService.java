package com.example.service;

import java.util.List;

import com.example.domain.WordRank;

public interface WordsRankService {

	/**
	 * Generates List of WordRank objects.
	 * @param text - text to process.
	 * @return List of word rank.
	 */
	public List<WordRank> getWordsRankForText(String text);
	
	/**
	 * Store a custom words rank values in DB.
	 * @param wordsRank - List of WordRank objects.
	 */
	public void saveWordsRank(List<WordRank> wordsRank);
}
