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
  private dataset: string;

  constructor(private http: HttpClient) {
  }

  public getWordRank(word: string): Observable<WordRank> {
    return this.http.get<WordRank>(this.baseUrl + '/ranks/' + this.dataset + '?word=' + word);
  }

  public getWordRanks(words: Set<string>): Observable<Array<WordRank>> {
    const body = JSON.stringify(Array.from(words));
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Array<WordRank>>(this.baseUrl + '/ranks/' + this.dataset + '/words', body, httpOptions);
  }

  public setDataset(dataset: string) {
    this.dataset = dataset;
  }
}
