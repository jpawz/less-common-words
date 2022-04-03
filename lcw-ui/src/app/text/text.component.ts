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
  wordRanks: WordRank[];

  constructor(private wordRankService: WordRankService) { }

  onSubmit() {
    const words = this.text.split(' ');
    words.forEach(w => this.wordRankService.getWordRank(w).subscribe(data => {
      this.wordRanks.push(data);
    }));
  }

  ngOnInit(): void {
  }

}
