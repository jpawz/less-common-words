import { RankPipe } from './rank-pipe';
import { TextService } from '../services/text.service';

describe('RankPipe', () => {
  it('should return new for new word', () => {
    const pipe = new RankPipe();

    const rank = pipe.transform(TextService.NEW_WORD_RANK);

    expect(rank).toEqual('new');
  });

  it('should return the same rank for known word', () => {
    const pipe = new RankPipe();

    const rank = pipe.transform(1);

    expect(rank).toEqual('1');
  });
});
