import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Note } from '../../entities/note';
import { TextService } from '../../services/text.service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  @Output() noteEvent = new EventEmitter<Array<Note>>();

  text: string;
  notes: Array<Note>;

  constructor(private textService: TextService) { }

  onSubmit() {
    this.notes = this.textService.getNotes(this.text);
    this.noteEvent.emit(this.notes);
  }

  ngOnInit(): void {
  }

}
