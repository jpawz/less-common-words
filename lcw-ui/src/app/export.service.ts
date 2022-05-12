import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Note } from './note';

@Injectable({
  providedIn: 'root'
})
export class ExportService {

  private exportApiUrl: string;

  constructor(private http: HttpClient) {
    this.exportApiUrl = 'http://localhost:8080/export';
  }

  exportCSV(notes: Note[]): Observable<Blob> {
    const data = new Array<Object>();
    notes.forEach(note => data.push({ word: note.word, translation: note.translation, sentence: note.sentence }));

    const httpOptions: Object = {
      responseType: 'blob',
    };

    return this.http.post<Blob>(this.exportApiUrl + '/csv', data, httpOptions);
  }

}
