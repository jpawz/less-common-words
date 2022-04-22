import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { TextComponent } from './text.component';

describe('TextComponent', () => {
  let component: TextComponent;
  let fixture: ComponentFixture<TextComponent>;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TextComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(TextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should split text into words', () => {
    const text = 'Some text.';

    const words = TextComponent.getUniqueWords(text);

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

    const sentences = TextComponent.getSentences(text);

    expect(sentences.length).toBe(3);
    expect(sentences[1].includes('armour-like')).toBeTrue();
  });


});
