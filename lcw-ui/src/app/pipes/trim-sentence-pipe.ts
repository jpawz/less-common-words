import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'trim'
})
export class TrimSentencePipe implements PipeTransform {
    transform(sentence: string, word: string, maxSentenceLength: number = 100): string {
        if (sentence.length === 0) {
            return '';
        }
        const separator = ' ';
        const wordPosition = sentence.toLowerCase().indexOf(word.toLowerCase());

        if (this.isWordAtTheBeginningOfSentence(wordPosition, maxSentenceLength)) {
            const endOfSubstring = sentence.indexOf(separator, maxSentenceLength);
            return sentence.substring(0, endOfSubstring).trim() + '...';
        } else if (this.isWordAtTheEndOfSentence(wordPosition, sentence, maxSentenceLength)) {
            const beginningOfSubstring = sentence.lastIndexOf(separator, sentence.length - maxSentenceLength);
            return '...' + sentence.substring(beginningOfSubstring).trim();
        } else { // the word is somewhere in the middle of the sentence
            const sliceBeforeWord = sentence.lastIndexOf(separator, wordPosition - maxSentenceLength / 2);
            const sliceAfterWord = sentence.indexOf(separator, wordPosition + maxSentenceLength / 2);
            return '...' + sentence.substring(sliceBeforeWord, sliceAfterWord).trim() + '...';
        }
    }

    private isWordAtTheEndOfSentence(wordPosition: number, sentence: string, maxLen: number) {
        return wordPosition > (sentence.length - (maxLen / 2));
    }

    private isWordAtTheBeginningOfSentence(wordPosition: number, maxLen: number) {
        return wordPosition < (maxLen / 2);
    }
}
