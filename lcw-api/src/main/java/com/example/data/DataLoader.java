package com.example.data;

import java.io.BufferedReader;
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
public class DataLoader implements ApplicationRunner {

	private WordRankRepository repository;

	@Autowired
	public DataLoader(WordRankRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Resource resource = new ClassPathResource("google-10000-english-usa.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);

		List<WordRank> wordRanks = new ArrayList<>();
		int rank = 1;
		while (reader.ready()) {
			String line = reader.readLine();
			wordRanks.add(new WordRank(line, rank));
			rank++;
		}

		repository.saveAll(wordRanks);
	}

}
