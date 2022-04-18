export class Word {
    id: number;
    word: string;
    rank: number;
    translation: string;

    constructor(id: number, word: string, rank: number, translation: string) {
        this.id = id;
        this.word = word;
        this.rank = rank;
        this.translation = translation;
    }
}
