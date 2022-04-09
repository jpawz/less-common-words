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


});
