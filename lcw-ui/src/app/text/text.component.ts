import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Word } from '../word';
import { WordBuilder } from '../word/word-builder';
import { WordRankService } from '../wordrank-service';
import { TextService } from './text.service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  constructor(private wordRankService: WordRankService,
              private textService: TextService) { }

  text: string;
  sentences: Array<string>;
  words: Array<Word>;

  @Output() wordEvent = new EventEmitter<Array<Word>>();

  onSubmit() {
    const uniqueWords = this.textService.getUniqueWords(this.text);
    this.sentences = this.textService.getSentences(this.text);
    this.words = new Array();

    this.wordRankService.getWordRanks(uniqueWords).subscribe(data => {
      data.forEach(wordRank => {
        this.words.push(new WordBuilder().word(wordRank.word).id(wordRank.id).rank(wordRank.rank).build());
        uniqueWords.delete(wordRank.word);
      });
      uniqueWords.forEach(word => {
        const sentence = this.textService.getExampleSentence(word, this.sentences);
        this.words.push(new WordBuilder().word(word).sentence(sentence).build());
      });
      this.wordEvent.emit(this.words);
    });
  }

  ngOnInit(): void {
  }

}
