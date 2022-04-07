import { WordRank } from './../word-rank';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-word-rank-list',
  templateUrl: './word-rank-list.component.html',
  styleUrls: ['./word-rank-list.component.css']
})
export class WordRankListComponent implements OnInit {

  @Input() wordRanks: Set<WordRank>;

  constructor() {
  }

  ngOnInit(): void {
  }

}
