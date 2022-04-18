import { Component, Input, OnInit } from '@angular/core';
import { Word } from '../word';

@Component({
  selector: 'app-word-rank-list',
  templateUrl: './word-rank-list.component.html',
  styleUrls: ['./word-rank-list.component.css']
})
export class WordRankListComponent implements OnInit {

  @Input() words: Word[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
