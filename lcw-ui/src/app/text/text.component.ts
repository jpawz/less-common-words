import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Word } from '../word';
import { TextService } from './text.service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private textService: TextService) { }

  text: string;
  words: Array<Word>;

  @Output() wordEvent = new EventEmitter<Array<Word>>();

  onSubmit() {
    this.words = this.textService.getWords(this.text);
    this.wordEvent.emit(this.words);
  }

  ngOnInit(): void {
  }

}
