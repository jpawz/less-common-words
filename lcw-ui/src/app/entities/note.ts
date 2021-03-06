export class Note {
    id: number;
    word: string;
    rank: number;
    translation: string;
    sentence: string;
    sentences: Array<string>;

    constructor(id: number, word: string, rank: number, translation: string, sentences: Array<string>) {
        this.id = id;
        this.word = word;
        this.rank = rank;
        this.translation = translation;
        this.sentence = sentences[0];
        this.sentences = sentences;
    }
}
