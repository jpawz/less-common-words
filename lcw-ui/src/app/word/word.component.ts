import { Component, Input, OnInit } from '@angular/core';
import { TranslationService } from '../translation.service';
import { Word } from '../word';

@Component({
  selector: '[app-word]',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

  @Input() word: Word;

  constructor(private translationService: TranslationService) {
  }

  ngOnInit(): void {
  }

  translate(word: string) {
    this.translationService.getTranslation(word).subscribe(data => this.word.translation = data);
  }

}
