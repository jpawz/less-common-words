import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { WordRank } from '../word-rank';
import { WordRankService } from '../wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private wordRankService: WordRankService) { }

  text: string;
  wordRanks: Array<WordRank>;

  @Output() wordRanksEvent = new EventEmitter<Array<WordRank>>();

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

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      this.wordRanks = data;
      data.forEach(wordRank => {
        uniqueWords.delete(wordRank.word);
      });
      uniqueWords.forEach(word => {
        this.wordRanks.push(new WordRank(0, word, 0));
      });
      this.wordRanksEvent.emit(this.wordRanks);
    });
  }

  ngOnInit(): void {
  }

}
