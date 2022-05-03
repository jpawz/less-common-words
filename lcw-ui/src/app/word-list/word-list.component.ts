import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Word } from '../word';

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.css']
})
export class WordListComponent implements OnInit {

  dataSource = new MatTableDataSource<Word>([]);
  displayedColumns = ['rank', 'word', 'translation'];

  constructor() {
  }

  @Input()
  public set words(words: Word[]) {
    this.dataSource.data = words;
  }

  ngOnInit(): void {
  }

}
