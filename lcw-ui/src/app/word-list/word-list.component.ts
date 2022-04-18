import { Component, Input, OnInit } from '@angular/core';
import { Word } from '../word';

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.css']
})
export class WordListComponent implements OnInit {

  @Input() words: Word[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
