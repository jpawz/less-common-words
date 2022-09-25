package com.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@Component
public class WordRankDataLoader implements ApplicationRunner {

    private WordRankRepository repository;

    @Autowired
    public WordRankDataLoader(WordRankRepository repository) {
	this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {

	repository.getDbNames().forEach(dbName -> {
	    try {
		Resource resource = new ClassPathResource(dbName);
		InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);

		List<WordRank> wordRanks = new ArrayList<>();
		int rank = 1;
		while (reader.ready()) {
		    String line = reader.readLine();
		    wordRanks.add(new WordRank(line, rank));
		    rank++;
		}

		repository.saveAll(dbName, wordRanks);
	    } catch (IOException exception) {
		throw new RuntimeException(exception);
	    }
	});
    }

}
