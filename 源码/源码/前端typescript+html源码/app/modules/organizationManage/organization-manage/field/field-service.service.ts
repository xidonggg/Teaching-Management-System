import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from "sweetalert2";
import {SchoolYear} from '../school-year/schoolyear-service.service';

@Injectable({
  providedIn: 'root'
})
export class FieldServiceService {

  private getAllUrl = "/springMVC-spring-hibernate/schoolyield/list";
  private addUrl = "/springMVC-spring-hibernate/schoolyield/save";
  private deleteUrl = "/springMVC-spring-hibernate/schoolyield/delete?_method=DELETE";
  private getSchools = "/springMVC-spring-hibernate/organization/getschool";
  private apiAnswer :any;
  constructor(public http: HttpClient) { }

  getAllFields(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllUrl, {observe: 'response'}
    )
  }
  getAllSchools(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getSchools, {observe: 'response'}
    )
  }
  /*
* 用post方法请求
* */
  addField (field: Field):Observable<HttpResponse<any>> {
    let body = JSON.stringify(field);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("postField:");
    console.log(field);
    return this.http.post<HttpResponse<any>>(this.addUrl, body, httpOptions)
  }
  deleteField (id: string):Observable<HttpResponse<any>> {
    //   let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    console.log("进入删除方法了，id:");
    console.log(id);
    return this.http.post<HttpResponse<any>>(this.deleteUrl, "id="+ id, httpOptions)
  };
}

export class Field{
  constructor(
    id:string,
    name: string,
    school: string,
    location: string,
    population: number
  ){}
}
export class Org{
  constructor(
    id:string,
    name:string
  ){}
}
