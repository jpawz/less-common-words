import { Component } from '@angular/core';
import { Word } from './word';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Less Common Words';

  words: Word[];

  receiveWords($event: Array<Word>) {
    this.words = $event;
  }
}
