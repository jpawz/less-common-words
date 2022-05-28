import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Less Common Words';

  text: string;

  receiveText($event: string) {
    this.text = $event;
  }
}
