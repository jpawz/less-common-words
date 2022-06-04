package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnkiApkgTest {
	AnkiApkg ankiApkg;

	@BeforeEach
	public void setUp() throws IOException {
		ankiApkg = new AnkiApkg(new ByteArrayOutputStream());
	}

	@Test
	void fileShouldBeAdded() {
		assertThat(ankiApkg.addToArchive("someFile", "file content".getBytes())).isTrue();
	}

	@Test
	void emptyFileShouldNotBeAdded() {
		assertThat(ankiApkg.addToArchive("filename", "".getBytes())).isFalse();
	}
}
