import { WordRankService } from './services/wordrank-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { HttpClientModule } from '@angular/common/http';
import { TextComponent } from './components/text/text.component';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { RankPipe } from './pipes/rank-pipe';

@NgModule({
  declarations: [
    AppComponent,
    NoteListComponent,
    TextComponent,
    RankPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatCheckboxModule
  ],
  providers: [WordRankService],
  bootstrap: [AppComponent]
})
export class AppModule { }
