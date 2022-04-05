import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WordRankListComponent } from './word-rank-list.component';

describe('WordRankListComponent', () => {
  let component: WordRankListComponent;
  let fixture: ComponentFixture<WordRankListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WordRankListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WordRankListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});