import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

export interface BackendResponse {
  hostIp: string[];
  date: string;
  time: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  date: Date;
  serverResponse: BackendResponse;

  constructor(private http: HttpClient) {
    // Intentionally empty
  }

  onDateChange(event: MatDatepickerInputEvent<any>) {
    this.date = event.value;

    this.http.get<BackendResponse>('http://localhost:3000/ip-date-info').subscribe(response => this.serverResponse = response);
  }
}
