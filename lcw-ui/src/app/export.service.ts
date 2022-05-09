import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Word } from './word';

@Injectable({
  providedIn: 'root'
})
export class ExportService {

  private exportApiUrl: string;

  constructor(private http: HttpClient) {
    this.exportApiUrl = 'http://localhost:8080/export';
  }

  exportCSV(words: Word[]): Observable<Blob> {
    const data = new Array<Object>();
    words.forEach(w => data.push({ word: w.word, translation: w.translation, sentence: w.sentence }));

    const httpOptions: Object = {
      responseType: 'blob',
    };

    return this.http.post<Blob>(this.exportApiUrl + '/csv', data, httpOptions);
  }

}
