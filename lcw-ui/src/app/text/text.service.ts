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
    return exampleSentence;
  }

  
}
