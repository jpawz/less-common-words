import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { TranslationService } from '../translation.service';
import { Word } from '../word';

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.css']
})
export class WordListComponent implements OnInit {

  dataSource = new MatTableDataSource<Word>([]);
  displayedColumns = ['select', 'rank', 'word', 'action', 'translation', 'example'];
  selection = new SelectionModel<Word>(true, []);

  constructor(private translationService: TranslationService) {
  }

  @Input()
  public set words(words: Word[]) {
    this.dataSource.data = words;
  }

  ngOnInit(): void {
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
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
