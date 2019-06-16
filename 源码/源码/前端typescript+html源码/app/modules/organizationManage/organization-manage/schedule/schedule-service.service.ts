import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import Swal from "sweetalert2";
import {FinanceTask} from '../../../financeManage/finance-service.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScheduleServiceService {

  private token: string = '';
  private getAllScheduleUrl = "/springMVC-spring-hibernate/schedule/getSchedule";
  private deleteScheduleUrl = "/springMVC-spring-hibernate/schedule/deleteScheduleEvent";
  private saveScheduleUrl = "/springMVC-spring-hibernate/schedule/saveSechdule";
  constructor(private http:HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }
  getAllSchedule():Observable<HttpResponse<any>>{
    console.log(this.getAllScheduleUrl);
    return this.http.get<any>(
      this.getAllScheduleUrl, {observe: 'response'}
    );
  }
  deleteSchedule (id: string):Observable<HttpResponse<any>>{
    console.log("发送的id：");
    console.log(id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.deleteScheduleUrl, "id="+ id, httpOptions)
  };
  saveSchedule(schedule:Schedule) :Observable<HttpResponse<any>>{
    let body = JSON.stringify(schedule);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8',
        'token': this.token
      })
    };
    return this.http.post<HttpResponse<any>>(this.saveScheduleUrl, body, httpOptions);
  }
}
export class Schedule{
  constructor(
    public id:string,
    public startTime:string,
    public endTime:string,
    public remark:string
  ){}
}
