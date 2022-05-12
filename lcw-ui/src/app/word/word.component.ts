import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { TranslationService } from '../translation.service';
import { Note } from '../note';

@Component({
  selector: '[app-word]',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

  @Input() word: Note;

  constructor(private translationService: TranslationService) {
  }

  ngOnInit(): void {
  }

  translate(word: string) {
    this.translationService.getTranslation(word).subscribe(
      data => { this.word.translation = data; },
      (error: HttpErrorResponse) => {
        if (error.status === 404) {
          alert('Translation not found');
        }
      }
    );
  }

}
