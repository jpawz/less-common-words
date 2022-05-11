import { Pipe, PipeTransform } from '@angular/core';
import { TextService } from './text/text.service';

@Pipe({
    name: 'rank'
})
export class RankPipe implements PipeTransform {
    transform(rank: number): string {
        if (rank === TextService.NEW_WORD_RANK) {
            return 'new';
        } else {
            return rank.toString();
        }

    }
}
