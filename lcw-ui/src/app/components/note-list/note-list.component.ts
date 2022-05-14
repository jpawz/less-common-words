import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { TranslationService } from '../../services/translation.service';
import { Note } from '../../entities/note';
import { ExportService } from '../../services/export.service';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent {

  dataSource = new MatTableDataSource<Note>([]);
  displayedColumns = ['select', 'rank', 'word', 'action', 'translation', 'example'];
  selection = new SelectionModel<Note>(true, []);
  private sort: MatSort;

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.dataSource.sort = this.sort;
  }

  constructor(private translationService: TranslationService,
    private exportService: ExportService) {
  }

  @Input()
  public set notes(notes: Note[]) {
    this.dataSource.data = notes;
  }

  ngOnInit(): void {
    this.dataSource.sort = this.sort;
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
      data => { note.translation = data; },
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
