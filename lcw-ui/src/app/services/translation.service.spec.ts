import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { environment } from '../../environments/environment';
import { TranslationService } from './translation.service';

describe('TranslationService', () => {
  const baseUrl = environment.baseUrl;
  let httpTestingController: HttpTestingController;
  let service: TranslationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(TranslationService);
  });


  it('#getTranslation should return translation when translation is available', (done) => {
    const wordToBeTranslated = 'hello';
    const partOfTranslation = 'greeting';

    service.getTranslation(wordToBeTranslated).subscribe(data => {
      expect(data).toContain(partOfTranslation);
      done();
    });

    const testRequest = httpTestingController.expectOne(baseUrl + '/translate?word=' + wordToBeTranslated);

    testRequest.flush(partOfTranslation);
  });

  it('#getTranslation should use GET to retrieve translation', () => {
    service.getTranslation('hello').subscribe();

    const testRequest = httpTestingController.expectOne(baseUrl + '/translate?word=hello');

    expect(testRequest.request.method).toEqual('GET');
  });

});
