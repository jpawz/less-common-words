import { Injectable } from '@angular/core';
import { Note } from '../entities/note';
import { NoteBuilder } from '../entities/note-builder';
import { WordRankService } from './wordrank-service';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  static NEW_WORD_RANK = Number.MAX_SAFE_INTEGER;

  constructor(private wordRankService: WordRankService) { }

  getNotes(text: string): Note[] {
    const uniqueWords = this.getUniqueWords(text);
    const sentences = this.splitTextIntoSentences(text);
    const notes = new Array<Note>();

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      data.forEach(wordRank => {
        notes.push(new NoteBuilder().word(wordRank.word).id(wordRank.id).rank(wordRank.rank).build());
        uniqueWords.delete(wordRank.word);
      });

      uniqueWords.forEach(word => {
        const sentence = this.getExampleSentence(word, sentences);
        notes.push(new NoteBuilder().word(word).sentence(sentence).rank(TextService.NEW_WORD_RANK).build());
      });
    });

    return notes;
  }

  getUniqueWords(text: string): Set<string> {
    const splitIntoWordsPattern = new RegExp(/[;:,.()"]\s*|\s+/);
    const words = text.split(splitIntoWordsPattern);
    const filteredWords = words.filter(this.hasOnlyLetters);

    const uniqueWords = new Set<string>();

    filteredWords.forEach(w => uniqueWords.add(w.toLowerCase())
    );

    return uniqueWords;
  }

  splitTextIntoSentences(text: string): Array<string> {
    const sentences = text.replace(/([.?!])\s*(?=[A-Z])/g, '$1|').split('|');
    return sentences;
  }

  getExampleSentence(word: string, sentences: Array<string>): string {
    const exampleSentence = sentences.find(sentence => sentence.toLowerCase().includes(word.toLowerCase()));
    const maxSentenceLength = 150;
    if (exampleSentence.length > maxSentenceLength) {
      return this.getShorterSentence(exampleSentence, word, maxSentenceLength);
    } else {
      return exampleSentence;
    }
  }

  getShorterSentence(sentence: string, word: string, maxSentenceLength: number): string {
    const separator = ' ';
    const wordPosition = sentence.toLowerCase().indexOf(word.toLowerCase());

    if (this.isWordAtTheBeginningOfSentence(wordPosition, maxSentenceLength)) {
      const endOfSubstring = sentence.indexOf(separator, maxSentenceLength);
      return sentence.substring(0, endOfSubstring).trim() + '...';
    } else if (this.isWordAtTheEndOfSentence(wordPosition, sentence, maxSentenceLength)) {
      const beginningOfSubstring = sentence.lastIndexOf(separator, sentence.length - maxSentenceLength);
      return '...' + sentence.substring(beginningOfSubstring).trim();
    } else { // the word is somewhere in the middle of the sentence
      const sliceBeforeWord = sentence.lastIndexOf(separator, wordPosition - maxSentenceLength / 2);
      const sliceAfterWord = sentence.indexOf(separator, wordPosition + maxSentenceLength / 2);
      return '...' + sentence.substring(sliceBeforeWord, sliceAfterWord).trim() + '...';
    }
  }

  private hasOnlyLetters(word: string): boolean {
    return /^[a-zA-Z]+$/.test(word);
  }

  private isWordAtTheEndOfSentence(wordPosition: number, sentence: string, maxLen: number) {
    return wordPosition > (sentence.length - (maxLen / 2));
  }

  private isWordAtTheBeginningOfSentence(wordPosition: number, maxLen: number) {
    return wordPosition < (maxLen / 2);
  }

}
