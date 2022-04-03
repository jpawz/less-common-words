import { WordRankService } from './wordrank-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { WordRankListComponent } from './word-rank-list/word-rank-list.component';
import { HttpClientModule } from '@angular/common/http';
import { TextComponent } from './text/text.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    WordRankListComponent,
    TextComponent
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
