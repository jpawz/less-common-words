import { TestBed } from '@angular/core/testing';

import { TextService } from './text.service';

describe('TextService', () => {
  let service: TextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TextService);
  });

  it('should split text into words', () => {
    const text = 'Some text.';

    const words = service.getUniqueWords(text);

    expect(words.size).toBe(2);
    expect(words.has('some')).toBeTrue();
    expect(words.has('text')).toBeTrue();
  });

  it('should split text into sentences', () => {
    const text = `One morning, when Gregor Samsa woke from troubled dreams,
                   he found himself transformed in his bed into a horrible 
                  vermin. He lay on his armour-like back, and if he lifted his
                   head a little he could see his brown belly, slightly domed and 
                   divided by arches into stiff sections. The bedding was hardly 
                   able to cover it and seemed ready to slide off any moment.`;

    const sentences = service.getSentences(text);

    expect(sentences.length).toBe(3);
    expect(sentences[1].includes('armour-like')).toBeTrue();
  });

});
