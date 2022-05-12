import { Component } from '@angular/core';
import { Note } from './note';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Less Common Words';

  notes: Note[];

  receiveNotes($event: Array<Note>) {
    this.notes = $event;
  }
}
