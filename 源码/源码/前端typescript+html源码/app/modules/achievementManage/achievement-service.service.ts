import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class AchievementServiceService {

  private getMyClassUrl = "/springMVC-spring-hibernate/myclass/getMyclasses";
  private getMyTeachClassUrl = "/springMVC-spring-hibernate/myclass/getTeachClasses";
  private getMyCourseClassUrl = "/springMVC-spring-hibernate/course/getcourseClassSelectByClassId";
  private getMyCourseUrl = "/springMVC-spring-hibernate/course/getMyCourse";
  private token ="";
  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }

  getMyClasses(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyClassUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'token':this.token
        })
      }
    )
  };
  getMyCourse(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyCourseUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'token':this.token
        })
      }
    )
  }
  getMyTeachClass(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyTeachClassUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'token':this.token
        })
      }
    )
  };
  getMyCourseClass(classId :string) : Observable<HttpResponse<any>>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.getMyCourseClassUrl, "classId=" + classId, httpOptions)
  }

}
