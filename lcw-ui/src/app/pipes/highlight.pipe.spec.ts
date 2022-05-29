import { HighlightPipe } from './highlight.pipe';

describe('HighlightPipe', () => {
  it('should highligh a word', () => {
    const pipe = new HighlightPipe();
    const sentence = 'The sentence with a word.';
    const word = 'word';

    const result = pipe.transform(sentence, word);

    expect(result).toEqual('The sentence with a <strong>word</strong>.');
  });
});
