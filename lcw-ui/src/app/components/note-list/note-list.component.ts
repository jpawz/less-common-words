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
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  dataIsLoading: boolean;

  dataSource = new MatTableDataSource<Note>([]);
  displayedColumns = ['select', 'rank', 'word', 'action', 'translation', 'example'];
  selection = new SelectionModel<Note>(true, []);
  subscription: Subscription;
  private sort: MatSort;

  constructor(private translationService: TranslationService,
    private exportService: ExportService, private textService: TextService, private wordRankService: WordRankService) {
  }

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.sort.sort(({ id: 'rank', start: 'desc' }) as MatSortable);
    this.dataSource.sort = this.sort;
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.dataSource.paginator = this.paginator;
  }


  @Input()
  public set text(text: string) {
    const notes = this.textService.getNotes(text);
    this.dataSource.data = notes;
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
    this.translationService.getTranslation(note.word).subscribe(
      data => {
        note.translation = data;
      },
      (error: HttpErrorResponse) => {
        if (error.status === 404) {
          alert('Translation not found');
        }
      }
    );
  }

  exportSelectedToCSV() {
    if (this.selection.selected.length > 0) {
      this.exportService.exportCSV(this.selection.selected).subscribe(response => {
        const a = document.createElement('a');
        const objectUrl = URL.createObjectURL(response);
        a.href = objectUrl;
        a.download = 'cards.csv';
        a.click();
        URL.revokeObjectURL(objectUrl);
      });
    }
  }

}
