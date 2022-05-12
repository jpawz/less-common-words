import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Note } from '../note';
import { TextService } from './text.service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private textService: TextService) { }

  text: string;
  notes: Array<Note>;

  @Output() noteEvent = new EventEmitter<Array<Note>>();

  onSubmit() {
    this.notes = this.textService.getNotes(this.text);
    this.noteEvent.emit(this.notes);
  }

  ngOnInit(): void {
  }

}
