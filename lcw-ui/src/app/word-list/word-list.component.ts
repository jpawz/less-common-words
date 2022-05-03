import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { TranslationService } from '../translation.service';
import { Word } from '../word';

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.css']
})
export class WordListComponent implements OnInit {

  dataSource = new MatTableDataSource<Word>([]);
  displayedColumns = ['rank', 'word', 'action', 'translation'];

  constructor(private translationService: TranslationService) {
  }

  @Input()
  public set words(words: Word[]) {
    this.dataSource.data = words;
  }

  ngOnInit(): void {
  }

  translate(word: Word) {
    this.translationService.getTranslation(word.word).subscribe(
      data => { word.translation = data; },
      (error: HttpErrorResponse) => {
        if (error.status === 404) {
          alert('Translation not found');
        }
      }
    );
  }

}
