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
  wordRankDataset: Array<string>;

  constructor(private wordRankService: WordRankService) {
    this.wordRankDataset = new Array<string>();
  }

  onSubmit() {
    this.textEvent.emit(this.text);
  }

  ngOnInit(): void {
    this.wordRankDataset.push('de-top-10000');
    this.wordRankDataset.push('google-10000-english-usa');
  }

}
