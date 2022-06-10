import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'highlight'
})
export class HighlightPipe implements PipeTransform {

  transform(sentence: string, word: string): string {
    const regex = new RegExp('\\b' + word + '\\b', 'g');
    const index = sentence.toLowerCase().search(regex);
    return sentence.slice(0, index)
      + '<strong>' + sentence.slice(index, index + word.length) + '</strong>'
      + sentence.slice(index + word.length);
  }

}
