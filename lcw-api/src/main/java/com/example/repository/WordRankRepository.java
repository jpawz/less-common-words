package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.WordRank;

@Repository
public interface WordRankRepository extends CrudRepository<WordRank, Long> {

	WordRank findByWord(String word);

	List<WordRank> findByWordIn(List<String> words);

}
