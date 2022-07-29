package com.example.domain;

import java.util.Objects;

public class WordRank {

    private String word;

    private int rank;

    public WordRank(String word, int rank) {
	this.word = word;
	this.rank = rank;
    }

    public WordRank() {

    }

    public String getWord() {
	return word;
    }

    public void setWord(String word) {
	this.word = word;
    }

    public int getRank() {
	return rank;
    }

    public void setRank(int rank) {
	this.rank = rank;
    }

    @Override
    public int hashCode() {
	return Objects.hash(word);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	WordRank other = (WordRank) obj;
	return Objects.equals(word, other.word);
    }

    @Override
    public String toString() {
	return "{'" + this.word + "': " + this.rank + "}";
    }

}
