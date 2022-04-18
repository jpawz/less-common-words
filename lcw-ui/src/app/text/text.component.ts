import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Word } from '../word';
import { WordRankService } from '../wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private wordRankService: WordRankService) { }

  text: string;
  words: Array<Word>;

  @Output() wordRanksEvent = new EventEmitter<Array<Word>>();

  static getUniqueWords(text: string): Set<string> {
    const pattern = new RegExp(/[;:,.()"]\s*|\s+/);
    const words = text.split(pattern);
    const nonEmptyWords = words.filter(word => word.length > 0);

    const uniqueWords = new Set<string>();

    nonEmptyWords.forEach(w => uniqueWords.add(w.toLowerCase())
    );

    return uniqueWords;
  }

  onSubmit() {
    const uniqueWords = TextComponent.getUniqueWords(this.text);
    this.words = new Array();

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      data.forEach(wordRank => {
        this.words.push(new Word(wordRank.id, wordRank.word, wordRank.rank, ''));
      });
      this.words.forEach(w => {
        uniqueWords.delete(w.word);
      });
      uniqueWords.forEach(word => {
        this.words.push(new Word(0, word, 0, ''));
      });
      this.wordRanksEvent.emit(this.words);
    });
  }

  ngOnInit(): void {
  }

}
