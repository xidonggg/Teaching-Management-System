import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {colors} from './time-table-manage/calendar/demo-utils/colors';
import {addDays} from "date-fns";

@Injectable({
  providedIn: 'root'
})
export class TimeTableServiceService {
  private getTimeTableUrl = "/springMVC-spring-hibernate/timetable/getTimeTable";

  private token:string = "";
  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }
  getAllTimeTable(classId:string): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    };
    console.log(this.getTimeTableUrl);
    return this.http.post<HttpResponse<any>>(this.getTimeTableUrl, 'classId=' + classId, httpOptions);
  };

}

export class TimeTableEvent{
  constructor(
    title: string,
    color: string,
    start: string,
    end: string
  ){}
}
