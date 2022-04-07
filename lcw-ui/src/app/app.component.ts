import { Component } from '@angular/core';
import { WordRank } from './word-rank';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Less Common Words';

  wordRanks: Set<WordRank>;

  receiveWordRanks($event) {
    this.wordRanks = $event;
  }
}
