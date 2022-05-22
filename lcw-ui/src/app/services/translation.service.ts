import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

/**
 * Service for providing translations of words.
 */
@Injectable({
  providedIn: 'root'
})
export class TranslationService {
  baseUrl = environment.baseUrl;
  private translationApiUrl: string;

  constructor(private http: HttpClient) {
    this.translationApiUrl = this.baseUrl + '/translate';
  }

  /**
   * Returns translation as a result of GET request to sever.
   *
   * @param word word to translate
   * @returns Observable of string
   */
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
