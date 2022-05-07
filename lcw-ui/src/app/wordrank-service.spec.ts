import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { WordRank } from './word-rank';

import { WordRankService } from './wordrank-service';

describe('WordRankService', () => {
    let httpTestingController: HttpTestingController;
    let service: WordRankService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule]
        });

        httpTestingController = TestBed.inject(HttpTestingController);
        service = TestBed.inject(WordRankService);
    });

    afterEach(() => {
        httpTestingController.verify();
    });

    it('#getWordRank should return expected data', (done) => {
        const expectedData: WordRank = { id: 1, rank: 1, word: 'the' };

        service.getWordRank('the').subscribe(data => {
            expect(data).toEqual(expectedData);
            done();
        });

        const testRequest = httpTestingController.expectOne('http://localhost:8080/api?word=the');

        testRequest.flush(expectedData);
    });

    it('#getWordRank should use GET to retrieve single data', () => {
        service.getWordRank('the').subscribe();

        const testRequest = httpTestingController.expectOne('http://localhost:8080/api?word=the');


        expect(testRequest.request.method).toEqual('GET');
    });


    it('#getWordRanks should return list of data', (done) => {
        const requestData = new Set(['the', 'and']);
        const expectedData: WordRank[] =
            [{ id: 1, rank: 1, word: 'the' },
            { id: 3, rank: 1, word: 'and' }];

        service.getWordRanks(requestData).subscribe(data => {
            expect(data).toEqual(expectedData);
            done();
        });

        const testRequest = httpTestingController.expectOne('http://localhost:8080/api/words');

        testRequest.flush(expectedData);
    });

    it('#getWordRanks should use POST to retrieve multiple data', () => {
        service.getWordRanks(new Set(['word'])).subscribe();

        const testRequest = httpTestingController.expectOne('http://localhost:8080/api/words');

        expect(testRequest.request.method).toEqual('POST');
    });

    it('#getWordRanks should return data for only known words', (done) => {
        const requestData = new Set(['the', 'itsNotAWord']);
        const expectedData: WordRank[] = [{ id: 1, rank: 1, word: 'the' }];

        service.getWordRanks(requestData).subscribe(data => {
            expect(data).toEqual(expectedData);
            done();
        });

        const testRequest = httpTestingController.expectOne('http://localhost:8080/api/words');

        testRequest.flush(expectedData);
    });

});
