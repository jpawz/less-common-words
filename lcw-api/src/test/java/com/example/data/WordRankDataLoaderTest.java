package com.example.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.example.repository.WordRankRepository;

class WordRankDataLoaderTest {

    @Test
    void shouldFindResources() throws IOException {
	WordRankRepository repository = new WordRankRepository();
	WordRankDataLoader dataLoader = new WordRankDataLoader(repository);

	int numberOfDbsBefore = repository.getDatasetNames().size();
	dataLoader.run(null);
	int numberOfDbsAfter = repository.getDatasetNames().size();

	assertThat(numberOfDbsAfter).isGreaterThan(numberOfDbsBefore);
    }

}
