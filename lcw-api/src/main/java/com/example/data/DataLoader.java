package com.example.data;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
		File file = resource.getFile();

		List<WordRank> wordRanks = new ArrayList<>();

		try (Stream<String> words = Files.lines(file.toPath())) {
			AtomicInteger rank = new AtomicInteger(1);
			words.forEachOrdered(w -> {
				wordRanks.add(new WordRank(w, rank.getAndIncrement()));
			});
		}

		repository.saveAll(wordRanks);
	}

}
