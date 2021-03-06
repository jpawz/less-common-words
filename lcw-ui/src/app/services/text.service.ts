import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, finalize, Observable, of } from 'rxjs';
import { Note } from '../entities/note';
import { NoteBuilder } from '../entities/note-builder';
import { WordRankService } from './wordrank-service';

/**
 * Service for handling text component: it has methods to prepare Notes from the given text content.
 */
@Injectable({
  providedIn: 'root'
})
export class TextService {

  static readonly newWordRank = Number.MAX_SAFE_INTEGER;

  public dataIsLoading: Observable<boolean>;

  private loadingSubject: BehaviorSubject<boolean>;


  constructor(private wordRankService: WordRankService) {
    this.loadingSubject = new BehaviorSubject<boolean>(false);
    this.dataIsLoading = this.loadingSubject.asObservable();
  }

  /**
   * Returns an array of Notes from submitted text.
   *
   * @param text the text content for analysis
   * @returns array of Note
   */
  getNotes(text: string): Note[] {
    this.loadingSubject.next(true);
    const uniqueWords = this.getUniqueWords(text);
    const sentences = this.splitTextIntoSentences(text);
    const notes = new Array<Note>();

    this.wordRankService.getWordRanks(uniqueWords).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(data => {
        data.forEach(wordRank => {
          const sentencesForWord = this.getExampleSentences(wordRank.word, sentences);
          notes.push(new NoteBuilder().word(wordRank.word)
            .sentences(sentencesForWord).sentence(sentencesForWord[0]).id(wordRank.id).rank(wordRank.rank).build());
          uniqueWords.delete(wordRank.word);
        });

        uniqueWords.forEach(word => {
          const sentencesForWord = this.getExampleSentences(word, sentences);
          notes.push(new NoteBuilder().word(word).sentences(sentencesForWord)
            .sentence(sentencesForWord[0]).rank(TextService.newWordRank).build());
        });
      });

    return notes;
  }

  getUniqueWords(text: string): Set<string> {
    const splitIntoWordsPattern = new RegExp(/[;:,.()"]\s*|\s+/);
    const words = text.split(splitIntoWordsPattern);
    const filteredWords = words.filter(this.hasOnlyLetters);

    const uniqueWords = new Set<string>();

    filteredWords.forEach(w => uniqueWords.add(w.toLowerCase()));

    return uniqueWords;
  }

  splitTextIntoSentences(text: string): Array<string> {
    const minimumSentenceLength = 10;
    const sentences = text.split(/((?<=[^A-Z].[.?!]) +(?=[A-Z]))|[\n]/g).filter(sentence => sentence?.length > minimumSentenceLength);
    return sentences;
  }

  getExampleSentences(word: string, sentences: Array<string>): Array<string> {
    const regex = new RegExp('\\b' + word.toLowerCase() + '\\b', 'g');
    const exampleSentence = sentences.filter(sentence => sentence.toLowerCase().search(regex) > -1);
    return exampleSentence;
  }

  private hasOnlyLetters(word: string): boolean {
    return /^[a-zA-Z]+$/.test(word);
  }

}
