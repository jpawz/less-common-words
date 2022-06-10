import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'highlight'
})
export class HighlightPipe implements PipeTransform {

  transform(sentence: string, word: string): string {
    const regex = new RegExp('\\b' + word + '\\b', 'g');
    return sentence.replace(regex, '<strong>' + word + '</strong>');
  }

}
