import { Pipe, PipeTransform } from '@angular/core';
import { TextService } from '../services/text.service';

@Pipe({
    name: 'rank'
})
export class RankPipe implements PipeTransform {
    transform(rank: number): string {
        if (rank === TextService.newWordRank) {
            return 'new';
        } else {
            return rank.toString();
        }

    }
}
