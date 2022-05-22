import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Note } from '../entities/note';
import { environment } from '../../environments/environment';

/**
 * Service for exporting Notes to desired file format.
 */
@Injectable({
  providedIn: 'root'
})
export class ExportService {

  baseUrl = environment.baseUrl;
  private exportApiUrl: string;

  constructor(private http: HttpClient) {
    this.exportApiUrl = this.baseUrl + '/export';
  }

  /**
   * Returns an Observable of Blob as a result of POST request to server.
   *
   * @param notes array of Note to export
   * @returns Observable of Blob
   */
  exportCSV(notes: Note[]): Observable<Blob> {
    const data = new Array<Object>();
    notes.forEach(note => data.push({ word: note.word, translation: note.translation, sentence: note.sentence }));

    const httpOptions: Object = {
      responseType: 'blob',
    };

    return this.http.post<Blob>(this.exportApiUrl + '/csv', data, httpOptions);
  }

}
