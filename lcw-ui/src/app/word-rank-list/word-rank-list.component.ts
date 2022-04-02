import { WordRankService } from './../wordrank-service';
import { WordRank } from './../word-rank';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-word-rank-list',
  templateUrl: './word-rank-list.component.html',
  styleUrls: ['./word-rank-list.component.css']
})
export class WordRankListComponent implements OnInit {

  wordRank: WordRank;

  constructor(private wordRankService: WordRankService) { }

  ngOnInit(): void {
    this.wordRankService.getWordRank('example').subscribe(data => {
      this.wordRank = data;
    });
  }

}
