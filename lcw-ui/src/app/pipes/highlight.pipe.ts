import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'highlight'
})
export class HighlightPipe implements PipeTransform {

  transform(sentence: string, word: string): string {
    return sentence.replace(word, '<strong>' + word + '</strong>');
  }

}
