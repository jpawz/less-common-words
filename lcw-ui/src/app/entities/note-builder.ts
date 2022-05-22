import { Note } from './note';

export class NoteBuilder {
    private readonly note: Note;

    constructor() {
        this.note = {
            id: 0,
            word: '',
            rank: 0,
            translation: '',
            sentence: ''
        };
    }

    id(id: number): NoteBuilder {
        this.note.id = id;
        return this;
    }

    word(word: string): NoteBuilder {
        this.note.word = word;
        return this;
    }

    rank(rank: number): NoteBuilder {
        this.note.rank = rank;
        return this;
    }

    translation(translation: string): NoteBuilder {
        this.note.translation = translation;
        return this;
    }

    sentence(sentence: string): NoteBuilder {
        this.note.sentence = sentence;
        return this;
    }


    build(): Note {
        return this.note;
    }
}
