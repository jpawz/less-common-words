import { SelectionModel } from '@angular/cdk/collections';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, MatSortable } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { TextService } from 'src/app/services/text.service';
import { Note } from '../../entities/note';
import { ExportService } from '../../services/export.service';
import { TranslationService } from '../../services/translation.service';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css'],
})
export class NoteListComponent implements OnInit {

  dataIsLoading: boolean;

  dataSource = new MatTableDataSource<Note>([]);
  displayedColumns = ['select', 'rank', 'word', 'action', 'translation', 'example'];
  selection = new SelectionModel<Note>(true, []);
  subscription: Subscription;
  expandedNote: Note | null;
  private paginator: MatPaginator;
  private sort: MatSort;

  constructor(private translationService: TranslationService,
    private exportService: ExportService, private textService: TextService) {
  }

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
  }

  @ViewChild(MatPaginator) set amtPaginator(pg: MatPaginator) {
    this.paginator = pg;
    this.dataSource.paginator = this.paginator;
  }


  @Input()
  public set text(text: string) {
    const notes = this.textService.getNotes(text);
    this.dataSource.data = notes;
    this.selection.clear();
  }

  ngOnInit(): void {
    this.subscription = this.textService.dataIsLoading.subscribe(progress => this.dataIsLoading = progress);
    setTimeout(() => {
      this.dataSource.sort = this.sort;
      this.sort?.sort(({ id: 'rank', start: 'desc' }) as MatSortable);
    }, 1000);
  }

  isNothingSelected(): boolean {
    return this.selection.selected.length === 0;
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

  translate(note: Note) {
    this.translationService.getTranslation(note.word).subscribe({
      next: (data) => note.translation = data,
      error: (error: HttpErrorResponse) => {
        if (error.status === 404) {
          alert('Translation not found');
        }
      }
    });
  }

  exportSelected(format: string) {
    if (this.selection.selected.length > 0) {
      this.exportService.export(this.selection.selected, format).subscribe(response => {
        const a = document.createElement('a');
        const objectUrl = URL.createObjectURL(response);
        a.href = objectUrl;
        switch (format.substring(0, 3)) {
          case 'csv':
            a.download = 'cards.csv';
            break;
          case 'ank':
            a.download = 'cards.apkg';
            break;
        }
        a.click();
        URL.revokeObjectURL(objectUrl);
      });
    }
  }

  selectedExample(note: Note, i: number) {
    note.sentence = note.sentences[i];
  }

}
