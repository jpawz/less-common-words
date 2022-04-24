import { HttpClient, HttpHandler } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Word } from '../word';
import { WordBuilder } from './word-builder';

import { WordComponent } from './word.component';

describe('WordComponent', () => {
  @Component({
    selector: 'word-component',
    template: '<component-under-test [word]="word"></component-under-test>'
  })
  class TestHostComponent {
    private word: Word;

    setInput(word: Word) {
      this.word = word;
    }
  }

  let component: TestHostComponent;
  let fixture: ComponentFixture<TestHostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WordComponent, TestHostComponent ],
      providers: [HttpClient, HttpHandler]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestHostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    component.setInput(new WordBuilder().word('anything').build());
    expect(component).toBeTruthy();
  });

});
