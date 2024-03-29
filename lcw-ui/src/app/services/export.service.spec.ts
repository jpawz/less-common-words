import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { ExportService } from './export.service';
import { Note } from '../entities/note';
import { environment } from '../../environments/environment';

describe('ExportService', () => {
  const baseUrl = environment.baseUrl;
  let service: ExportService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    service = TestBed.inject(ExportService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('#exportCSV should response with csv', (done) => {
    const notesToSubmit: Note[] = [{ id: 0, rank: 0, sentences: [''], translation: 'the translation', word: 'word', sentence: '' }];
    const expectedCsv = new Blob(['word,the translation,,'], { type: 'text/csv' });

    service.export(notesToSubmit, 'csv').subscribe(async data => {
      const text = await (new Response(data)).text();
      expect(text).toEqual('word,the translation,,');
      done();
    });

    const testRequest = httpTestingController.expectOne(baseUrl + '/export/csv');

    testRequest.flush(expectedCsv);
  });

  it('#exportCSV should use POST to retrieve csv data', () => {
    const notesToSubmit: Note[] = [{ id: 0, rank: 0, sentences: [''], translation: 'the translation', word: 'word', sentence: '' }];
    service.export(notesToSubmit, 'csv').subscribe();

    const testRequest = httpTestingController.expectOne(baseUrl + '/export/csv');

    expect(testRequest.request.method).toEqual('POST');
  });

  it('#exportCSV POST request should contain correct body', () => {
    const notesToSubmit: Note[] = [{ id: 0, rank: 0, sentences: [''], translation: 'the translation', word: 'word', sentence: '' }];
    service.export(notesToSubmit, 'csv').subscribe();

    const testRequest = httpTestingController.expectOne(baseUrl + '/export/csv');

    expect(testRequest.request.body).toContain({ word: 'word', translation: 'the translation', sentence: '' });
  });

});
