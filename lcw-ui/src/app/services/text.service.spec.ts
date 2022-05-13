import { TestBed } from '@angular/core/testing';
import { WordRankService } from '../services/wordrank-service';

import { TextService } from './text.service';

describe('TextService', () => {
  let service: TextService;
  let wordRankServiceSpy: jasmine.SpyObj<WordRankService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('WordRankService', ['getWordRanks']);
    TestBed.configureTestingModule({
      providers: [
        TextService, { provide: WordRankService, useValue: spy }
      ]
    });
    service = TestBed.inject(TextService);
    wordRankServiceSpy = TestBed.inject(WordRankService) as jasmine.SpyObj<WordRankService>;
  });

  it('#getUniqueWords should extract unique words from', () => {
    const text = 'Some text: the text.';

    const words = service.getUniqueWords(text);

    expect(words.size).toBe(3);
    expect(words.has('some')).toBeTrue();
    expect(words.has('text')).toBeTrue();
    expect(words.has('the')).toBeTrue();
  });

  it('#splitTextIntoSentences should split text', () => {
    const text = `One morning, when Gregor Samsa woke from troubled dreams,
                   he found himself transformed in his bed into a horrible 
                  vermin. He lay on his armour-like back, and if he lifted his
                   head a little he could see his brown belly, slightly domed and 
                   divided by arches into stiff sections. The bedding was hardly 
                   able to cover it and seemed ready to slide off any moment.`;

    const sentences = service.splitTextIntoSentences(text);

    expect(sentences.length).toBe(3);
    expect(sentences[1].includes('armour-like')).toBeTrue();
  });

  it('#getUniqueWords should filter out words with characters other than letters', () => {
    const text = 'The t3xt';

    const words = service.getUniqueWords(text);

    expect(words.size).toEqual(1);
    expect(words).toContain('the');
  });

  it('#getExampleSentence should return a sentence containing the word', () => {
    const sentences = ['The first sentence.', 'The second sentence.'];
    const word = 'second';

    const exampleSentence = service.getExampleSentence(word, sentences);

    expect(exampleSentence).toEqual('The second sentence.');
  });
});
