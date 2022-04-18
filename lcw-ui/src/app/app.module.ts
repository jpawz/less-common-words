import { WordRankService } from './wordrank-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { WordListComponent } from './word-list/word-list.component';
import { HttpClientModule } from '@angular/common/http';
import { TextComponent } from './text/text.component';
import { FormsModule } from '@angular/forms';
import { WordComponent } from './word/word.component';

@NgModule({
  declarations: [
    AppComponent,
    WordListComponent,
    TextComponent,
    WordComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [WordRankService],
  bootstrap: [AppComponent]
})
export class AppModule { }
