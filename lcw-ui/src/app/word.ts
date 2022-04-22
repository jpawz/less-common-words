export class Word {
    id: number;
    word: string;
    rank: number;
    translation: string;
    sentence: string;

    constructor(id: number, word: string, rank: number, translation: string, sentence: string) {
        this.id = id;
        this.word = word;
        this.rank = rank;
        this.translation = translation;
        this.sentence = sentence;
    }
}
