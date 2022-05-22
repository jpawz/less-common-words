import { TrimSentencePipe } from './trim-sentence-pipe';

describe('TrimSentencePipe', () => {
  let pipe: TrimSentencePipe;
  const sentence = 'He lay on his armour-like back, and if he lifted his head '
    + 'a little he could see his brown belly, slightly domed and divided by arches into stiff sections.';

  beforeEach(() => {
    pipe = new TrimSentencePipe();
  });

  it('should shorten too long sentence with the word in the middle', () => {
    const word = 'brown';
    const maxLen = 80;
    const expectedShortenedSentence = '...lifted his head a little he could see his brown belly, slightly domed and divided by...';

    const shortenedSentence = pipe.transform(sentence, word, maxLen);

    expect(shortenedSentence).toEqual(expectedShortenedSentence);
  });

  it('should shorten too long sentence with the word at the beginning', () => {
    const word = 'lay';
    const maxLen = 80;
    const expectedShortenedSentence = 'He lay on his armour-like back, and if he lifted his head a little he could see his...';

    const shortenedSentence = pipe.transform(sentence, word, maxLen);

    expect(shortenedSentence).toEqual(expectedShortenedSentence);
  });

  it('should shorten too long sentence with the word at the end', () => {
    const word = 'sections';
    const maxLen = 80;
    const expectedShortenedSentence = '...could see his brown belly, slightly domed and divided by arches into stiff sections.';

    const shortenedSentence = pipe.transform(sentence, word, maxLen);

    expect(shortenedSentence).toEqual(expectedShortenedSentence);
  });

  it('should return empty string when empty string is submitted', () => {
    const emptySentence = '';
    const word = 'word';

    const shortenedSentence = pipe.transform(emptySentence, word);

    expect(shortenedSentence).toHaveSize(0);
  });
});
