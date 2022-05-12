import { Note } from './note';

export class NoteBuilder {
    private readonly _note: Note;

    constructor() {
        this._note = {
            id: 0,
            word: '',
            rank: 0,
            translation: '',
            sentence: ''
        };
    }

    id(id: number): NoteBuilder {
        this._note.id = id;
        return this;
    }

    word(word: string): NoteBuilder {
        this._note.word = word;
        return this;
    }

    rank(rank: number): NoteBuilder {
        this._note.rank = rank;
        return this;
    }

    translation(translation: string): NoteBuilder {
        this._note.translation = translation;
        return this;
    }

    sentence(sentence: string): NoteBuilder {
        this._note.sentence = sentence;
        return this;
    }


    build(): Note {
        return this._note;
    }
}
