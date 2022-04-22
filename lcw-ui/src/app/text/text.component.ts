import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Word } from '../word';
import { WordBuilder } from '../word-builder';
import { WordRankService } from '../wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private wordRankService: WordRankService) { }

  text: string;
  sentences: Array<string>;
  words: Array<Word>;

  @Output() wordEvent = new EventEmitter<Array<Word>>();

  static getUniqueWords(text: string): Set<string> {
    const pattern = new RegExp(/[;:,.()"]\s*|\s+/);
    const words = text.split(pattern);
    const nonEmptyWords = words.filter(word => word.length > 0);

    const uniqueWords = new Set<string>();

    nonEmptyWords.forEach(w => uniqueWords.add(w.toLowerCase())
    );

    return uniqueWords;
  }

  static getSentences(text: string): Array<string> {
    const sentences = text.replace(/([.?!])\s*(?=[A-Z])/g, '$1|').split('|');
    return sentences;
  }

  static getExampleSentence(word: string, sentences: Array<string>): string {
    const exampleSentence = sentences.find(sentence => sentence.toLowerCase().includes(word.toLowerCase()));
    return exampleSentence;
  }

  onSubmit() {
    const uniqueWords = TextComponent.getUniqueWords(this.text);
    this.sentences = TextComponent.getSentences(this.text);
    this.words = new Array();

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      data.forEach(wordRank => {
        this.words.push(new WordBuilder().word(wordRank.word).id(wordRank.id).rank(wordRank.rank).build());
        uniqueWords.delete(wordRank.word);
      });
      uniqueWords.forEach(word => {
        const sentence = TextComponent.getExampleSentence(word, this.sentences);
        this.words.push(new WordBuilder().word(word).sentence(sentence).build());
      });
      this.wordEvent.emit(this.words);
    });
  }

  ngOnInit(): void {
  }

}
