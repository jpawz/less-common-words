import { WordRank } from './word-rank';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WordRankService {

  private wordRankUrl: string;

  constructor(private http: HttpClient) {
    this.wordRankUrl = 'http://localhost:8080/api';
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
