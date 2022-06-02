package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;


public class AnkiApkgTest {
	AnkiApkg ankiApkg;

	@TempDir
	Path apkgFile;

	@BeforeEach
	public void setUp() throws IOException {
		apkgFile = Paths.get(apkgFile, "anki2.apkg");
		ankiApkg = new AnkiApkg(apkgFile);
	}

	@Test
	public void fileShouldBeAdded() {
		assertThat(ankiApkg.addToArchive("someFile", "file content".getBytes())).isTrue();
	}

	@Test
	public void emptyFileShouldNotBeAdded() {
		assertThat(ankiApkg.addToArchive("filename", "".getBytes())).isFalse();
	}
}
