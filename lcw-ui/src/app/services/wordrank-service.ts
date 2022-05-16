import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { WordRank } from '../entities/word-rank';

@Injectable({
  providedIn: 'root'
})
export class WordRankService {

  baseUrl = environment.baseUrl;
  private wordRankUrl: string;

  constructor(private http: HttpClient) {
    this.wordRankUrl = this.baseUrl + '/api';
  }

  public getWordRank(word: string): Observable<WordRank> {
    return this.http.get<WordRank>(this.wordRankUrl + '?word=' + word);
  }

  public getWordRanks(words: Set<string>): Observable<Array<WordRank>> {
    const body = JSON.stringify(Array.from(words));
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Array<WordRank>>(this.wordRankUrl + '/words', body, httpOptions);
  }
}
