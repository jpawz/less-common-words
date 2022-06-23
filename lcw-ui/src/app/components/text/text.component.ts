import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { WordRankService } from 'src/app/services/wordrank-service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  @Output() textEvent = new EventEmitter<string>();

  text: string;
  nMostPopular: number;

  constructor(private wordRankService: WordRankService) { }

  onSubmit() {
    this.textEvent.emit(this.text);
    this.wordRankService.filter = this.nMostPopular;
  }

  ngOnInit(): void {
  }

}
