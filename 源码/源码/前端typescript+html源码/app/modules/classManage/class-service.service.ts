import { Injectable } from '@angular/core';
import Swal from "sweetalert2";
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClassServiceService {

  private token ="";
  private addClassUrl = "/springMVC-spring-hibernate/myclass/save";
  private updateClassUrl = "/springMVC-spring-hibernate/myclass/update?_method=PUT";
  private deleteClassUrl = "/springMVC-spring-hibernate/myclass/delete?_method=DELETE";
  private getAllClassUrl = "/springMVC-spring-hibernate/myclass/list";
  private getClassTree = "/springMVC-spring-hibernate/myclass/classtree";
  private searchClassUrl = "/springMVC-spring-hibernate/myclass/searchClass";
  private getMyClassUrl = "/springMVC-spring-hibernate/myclass/getMyclasses";
  private getTeacherClassUrl = "/springMVC-spring-hibernate/myclass/getTeachClasses";
  private getClassByIdUrl = "/springMVC-spring-hibernate/myclass/getClassByID";
  apiAnswer: any;

  private classes: Class[] = [
    new Class("123","实验班","班主任1,banzhuren2","屏峰教学楼","2016","屏峰校区",true,24),
    new Class("1123","精英班","班主任2,dsdsfds","屏峰教学楼","2016","屏峰校区",true,36)
  ];
  private class: Class =  new Class("123","实验班","班主任1,fsdfds","vfgd","2016","上海",true,24);
  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }
  getMyClass(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyClassUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Inherit auth from parent',
          'token' : this.token
        })
      }
    )
  }
  getTeachClass(): Observable<HttpResponse<any>>{
    return this.http.get<any>(
      this.getTeacherClassUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Inherit auth from parent',
          'token' : this.token
        })
      }
    )
  }

  getClassById(id:string): Observable<HttpResponse<any>>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.getClassByIdUrl, "classId="+ id, httpOptions)
  }
  getAllClass(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllClassUrl, {observe: 'response'}
    )
  };
  getAllClassTree(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getClassTree, {observe: 'response'}
    )
  };
  searchClass(remoteSearchClass: RemoteSearchClass): Observable<HttpResponse<any>>{
    console.log("筛选班级的参数：");
    console.log(remoteSearchClass);
    let body = JSON.stringify(remoteSearchClass);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    return this.http.post<HttpResponse<any>>(this.searchClassUrl, body, httpOptions);
  }
  updateClass(){
    Swal.fire({
      type: 'success',
      title: '更新成功',
    })
  }
  addClass(cclass: Class): Observable<HttpResponse<any>>{
    let body = JSON.stringify(cclass);
    console.log(body);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("poststudent:");
    console.log(cclass);
    return this.http.post<HttpResponse<any>>(this.addClassUrl, body, httpOptions);
  }
  getMyClassed(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyClassUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'token':this.token
    })}
    )
  }
  deleteClass(id :string): Observable<HttpResponse<any>>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    console.log("进入删除方法了，id:");
    console.log(id);
    return this.http.post<HttpResponse<any>>(this.deleteClassUrl, "id="+ id, httpOptions)
  }

}
export class Class{
  constructor(
    public id: string,
    public name: string,//名称
    public directors: string,//班主任，多个用,隔开
    public teachplace: string,//教学地点
    public startyear: string,//开班年份
    public school: string,//所属校区
    public isend: boolean,//是否已经结班
    public totalTime: number//班级存在时间长度，以月为单位
  ) { }
}
//远程筛选班级的封装类
export class RemoteSearchClass{
  constructor(
    public isend: string,
    public school: string,
    public startyear: string,
    public name: string
  ){}
}
