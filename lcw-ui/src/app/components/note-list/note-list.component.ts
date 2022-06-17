import { animate, state, style, transition, trigger } from '@angular/animations';
import { SelectionModel } from '@angular/cdk/collections';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, MatSortable } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { TextService } from 'src/app/services/text.service';
import { WordRankService } from 'src/app/services/wordrank-service';
import { Note } from '../../entities/note';
import { ExportService } from '../../services/export.service';
import { TranslationService } from '../../services/translation.service';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed, void', style({ height: '0px', minHeight: '0px' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
      transition('expanded <=> void', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)'))
    ])
  ],
})
export class NoteListComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  dataIsLoading: boolean;

  dataSource = new MatTableDataSource<Note>([]);
  displayedColumns = ['select', 'rank', 'word', 'action', 'translation', 'example'];
  selection = new SelectionModel<Note>(true, []);
  subscription: Subscription;
  expandedNote: Note | null;
  private sort: MatSort;

  constructor(private translationService: TranslationService,
    private exportService: ExportService, private textService: TextService, private wordRankService: WordRankService) {
  }

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.sort?.sort(({ id: 'rank', start: 'desc' }) as MatSortable);
    this.dataSource.sort = this.sort;
    this.sort?.sortChange.subscribe(() => this.paginator.pageIndex = 0);
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
    this.dataSource.sort = this.sort;
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

}
