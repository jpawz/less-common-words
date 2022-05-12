import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteListComponent } from './note-list.component';

describe('NoteListComponent', () => {
  let component: NoteListComponent;
  let fixture: ComponentFixture<NoteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ NoteListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
