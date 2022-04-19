import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  private translationApiUrl: string;

  constructor(private http: HttpClient) {
    this.translationApiUrl = 'http://localhost:8080/translate';
  }

  getTranslation(word: string): Observable<string> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'text/html;charset=UTF-8'
      }),
      responseType: 'text' as const
    };
    return this.http.get(this.translationApiUrl + '?word=' + word, httpOptions);
  }
}