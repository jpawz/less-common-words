import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  @Output() textEvent = new EventEmitter<string>();

  text: string;

  constructor() { }

  onSubmit() {
    this.textEvent.emit(this.text);
  }

  ngOnInit(): void {
  }

}
