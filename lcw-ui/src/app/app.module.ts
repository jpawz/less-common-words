import { TextFieldModule } from '@angular/cdk/text-field';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './components/app.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { TextComponent } from './components/text/text.component';
import { HighlightPipe } from './pipes/highlight.pipe';
import { RankPipe } from './pipes/rank-pipe';
import { WordRankService } from './services/wordrank-service';


@NgModule({
  declarations: [
    AppComponent,
    NoteListComponent,
    TextComponent,
    RankPipe,
    HighlightPipe
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatSortModule,
    MatTableModule,
    TextFieldModule
  ],
  providers: [WordRankService],
  bootstrap: [AppComponent]
})
export class AppModule { }
