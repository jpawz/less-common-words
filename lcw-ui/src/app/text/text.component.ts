import { Component, OnInit } from '@angular/core';
import { WordRank } from '../word-rank';
import { WordRankService } from '../wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  text: string;
  wordRanks: Set<WordRank>;

  constructor(private wordRankService: WordRankService) { }

  onSubmit() {
    const words = this.text.split(' ');
    const uniqueWords = new Set<string>();

    words.forEach(word => uniqueWords.add(word.toLowerCase()));

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      this.wordRanks = data;
    });
  }

  ngOnInit(): void {
  }

}
