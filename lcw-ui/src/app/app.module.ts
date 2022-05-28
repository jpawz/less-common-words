import { TextFieldModule } from '@angular/cdk/text-field';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './components/app.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { TextComponent } from './components/text/text.component';
import { RankPipe } from './pipes/rank-pipe';
import { TrimSentencePipe } from './pipes/trim-sentence-pipe';
import { WordRankService } from './services/wordrank-service';


@NgModule({
  declarations: [
    AppComponent,
    NoteListComponent,
    TextComponent,
    RankPipe,
    TrimSentencePipe
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    TextFieldModule
  ],
  providers: [WordRankService],
  bootstrap: [AppComponent]
})
export class AppModule { }
