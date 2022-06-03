package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AnkiApkgTest {
	AnkiApkg ankiApkg;

	@TempDir
	Path apkgFile;

	@BeforeEach
	public void setUp() throws IOException {
		apkgFile = Paths.get(apkgFile.toString(), "anki2.apkg");
		ankiApkg = new AnkiApkg(apkgFile);
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
