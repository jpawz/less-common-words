import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { WordRank } from '../word-rank';
import { WordRankService } from '../wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  text: string;
  wordRanks: Array<WordRank>;

  @Output() wordRanksEvent = new EventEmitter<Array<WordRank>>();

  constructor(private wordRankService: WordRankService) { }

  onSubmit() {
    const uniqueWords = this.getUniqueWords();

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

  private getUniqueWords(): Set<string> {
    const words = this.text.split(' ');
    const uniqueWords = new Set<string>();

    words.forEach(word => uniqueWords.add(word.toLowerCase()));
    return uniqueWords;
  }

  ngOnInit(): void {
  }

}
