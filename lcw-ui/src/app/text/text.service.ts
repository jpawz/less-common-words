import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor() { }

  getUniqueWords(text: string): Set<string> {
    const pattern = new RegExp(/[;:,.()"]\s*|\s+/);
    const words = text.split(pattern);
    const nonEmptyWords = words.filter(word => word.length > 0);

    const uniqueWords = new Set<string>();

    nonEmptyWords.forEach(w => uniqueWords.add(w.toLowerCase())
    );

    return uniqueWords;
  }

  getSentences(text: string): Array<string> {
    const sentences = text.replace(/([.?!])\s*(?=[A-Z])/g, '$1|').split('|');
    return sentences;
  }

  getExampleSentence(word: string, sentences: Array<string>): string {
    const exampleSentence = sentences.find(sentence => sentence.toLowerCase().includes(word.toLowerCase()));
    const maxSentenceLength = 150;
    if (exampleSentence.length > maxSentenceLength) {
      return this.shortenSentence(exampleSentence, word, maxSentenceLength);
    } else {
      return exampleSentence;
    }
  }

  shortenSentence(sentence: string, word: string, maxSentenceLength: number): string {
    const separator = ' ';
    const wordPosition = sentence.toLowerCase().indexOf(word.toLowerCase());
    let startOfSubstring: number;
    let endOfSubstring: number;

    if (this.isWordAtTheBeginningOfSentence(wordPosition, maxSentenceLength)) {
      startOfSubstring = 0;
      endOfSubstring = sentence.indexOf(separator, maxSentenceLength);
      return sentence.substring(startOfSubstring, endOfSubstring).trim() + '...';
    } else if (this.isWordAtTheEndOfSentence(wordPosition, sentence, maxSentenceLength)) {
      startOfSubstring = sentence.lastIndexOf(separator, sentence.length - maxSentenceLength);
      endOfSubstring = sentence.length;
      return '...' + sentence.substring(startOfSubstring, endOfSubstring).trim();
    } else { // the word is somewhere in the middle of the sentence
      startOfSubstring = sentence.lastIndexOf(separator, wordPosition - maxSentenceLength / 2);
      endOfSubstring = sentence.indexOf(separator, wordPosition + maxSentenceLength / 2);
      return '...' + sentence.substring(startOfSubstring, endOfSubstring).trim() + '...';
    }
  }

  private isWordAtTheEndOfSentence(wordPosition: number, sentence: string, maxLen: number) {
    return wordPosition > (sentence.length - (maxLen / 2));
  }

  private isWordAtTheBeginningOfSentence(wordPosition: number, maxLen: number) {
    return wordPosition < (maxLen / 2);
  }

}
