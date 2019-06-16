import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {HttpConnectServiceService} from '../../../../tools/http-connect-service.service';
import Swal from "sweetalert2";
import {Student} from '../../../studentManage/student-service.service';

@Injectable({
  providedIn: 'root'
})
export class SchoolyearServiceService {

  private getAllUrl = "/springMVC-spring-hibernate/year/list";
  private addUrl="/springMVC-spring-hibernate/year/save";
  private deleteUrl="/springMVC-spring-hibernate/year/delete?_method=DELETE";
  apiAnswer:any;
  constructor(public http: HttpClient) { }

  getAllYears(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllUrl, {observe: 'response'}
    )
  };
  /*
 * 用post方法请求
 * */
  addYear (schoolYear: SchoolYear): any {
    let body = JSON.stringify(schoolYear);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("postschoolYear:");
    console.log(schoolYear);
    this.http.post<HttpResponse<any>>(this.addUrl, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        this.apiAnswer = resp;
        // const keys = resp.headers.keys();
        // this.harders = keys.map(
        //   key => this.apiAnswer = {...resp.body}
        // )
        console.log("说好的100会返回")
        if(this.apiAnswer.code == '100'){
          Swal.fire({
            type: 'success',
            title: '添加成功',
            text: '添加了一条学生数据到数据库中',
            footer: '<a href>you are the best!</a>'
          })
        }else if(this.apiAnswer.code == '200'){
          Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href>Why do I have this issue?</a>'
          })}
      } );
    return this.apiAnswer;
  }

  deleteYear2 (id: string): Observable<HttpResponse<any>>  {
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
    return this.http.post<HttpResponse<any>>(this.deleteUrl, "id="+ id, httpOptions);
  };
}

export class SchoolYear{
  constructor(
    id: string,
    name: string,
    startTime: string,
    endTime: string
  ){}
}
