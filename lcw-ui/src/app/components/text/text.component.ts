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
  selectedDataset: string;

  constructor(private wordRankService: WordRankService) {
    wordRankService.getDatasets().subscribe({
      next: (data) => this.wordRankDataset = data
    });
    this.wordRankDataset = new Array<string>();
  }

  onSubmit() {
    this.wordRankService.setDataset(this.selectedDataset);
    this.textEvent.emit(this.text);
  }

  ngOnInit(): void {
    this.selectedDataset = this.wordRankDataset[0];
  }

}
