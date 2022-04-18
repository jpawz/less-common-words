import { Word } from './word';

describe('Word', () => {
  it('should create an instance', () => {
    expect(new Word(1, 'word', 1, '')).toBeTruthy();
  });
});
