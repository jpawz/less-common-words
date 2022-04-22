import { Word } from './word';

export class WordBuilder {
    private readonly _word: Word;

    constructor() {
        this._word = {
            id: 0,
            word: '',
            rank: 0,
            translation: '',
            sentence: ''
        };
    }

    id(id: number): WordBuilder {
        this._word.id = id;
        return this;
    }

    word(word: string): WordBuilder {
        this._word.word = word;
        return this;
    }

    rank(rank: number): WordBuilder {
        this._word.rank = rank;
        return this;
    }

    translation(translation: string): WordBuilder {
        this._word.translation = translation;
        return this;
    }

    sentence(sentence: string): WordBuilder {
        this._word.sentence = sentence;
        return this;
    }


    build(): Word {
        return this._word;
    }
}
