package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.WordRank;

@Repository
public interface WordRankRepository extends CrudRepository<WordRank, Long> {

	WordRank findByWord(String word);

}
