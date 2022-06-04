package com.example.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word_rank")
public class WordRank {

	public static final int RANK_IGNORE = 0;
	public static final int RANK_NEW = Integer.MAX_VALUE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String word;

	private int rank;

	public WordRank(String word, int rank) {
		this.word = word;
		this.rank = rank;
	}

	public WordRank() {

	}

	public long getId() {
		return id;
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
