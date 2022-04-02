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
}
