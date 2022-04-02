import { TestBed } from '@angular/core/testing';

import { WordRankService } from './wordrank-service';

describe('WordRankService', () => {
  let service: WordRankService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WordRankService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
