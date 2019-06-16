import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { HttpConnectServiceService} from '../../tools/http-connect-service.service';
import Swal from "sweetalert2";



@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {

  private students: Student[] = [];
  apiAnswer: any;
  harders: any;
  private addUrl = '/springMVC-spring-hibernate/person/save';
  private getByIdUrl = '/springMVC-spring-hibernate/person/get/';
  private getAllUrl = '/springMVC-spring-hibernate/person/list';
  private deleteUrl = '/springMVC-spring-hibernate/person/delete?_method=DELETE'
  private updateUrl = '/springMVC-spring-hibernate/person/update?_method=PUT'
  private getByclassId = '/springMVC-spring-hibernate/person/getStuByClassId';
  private getAvabileClassUrl = "/springMVC-spring-hibernate/myclass/getavaliableClass";
  private updateStuClassUrl = "/springMVC-spring-hibernate/person/distributeClsss";

  constructor(public http: HttpClient, private httpService: HttpConnectServiceService) {
   }
  getAllStudents(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllUrl, {observe: 'response'}
    )
  };
  getStudentById (id: string): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getByIdUrl + id, {observe: 'response'}
    )
  };
  getAvabileClass (): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAvabileClassUrl, {observe: 'response'}
    )
  };
  updateStuClass (classId: string,stuId:string): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.updateStuClassUrl, "classId="+ classId+"&stuId="+stuId, httpOptions)
  };
  getStudentByclassId (classId: string): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.getByclassId, "classId="+ classId, httpOptions)
  };
  /*
* 用post方法请求
* */
  deleteStudent (id: string) {
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
    this.http.post<HttpResponse<any>>(this.deleteUrl, "id="+ id, httpOptions)
      .pipe()
      .subscribe(resp => {
      } );
  };
  /*
* 用post方法请求
* */
  updateStudent (student: Student) {
    let body = JSON.stringify(student);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    new Promise((resolve, reject)=>{
      this.http.post<HttpResponse<any>>(this.updateUrl, body, httpOptions)
        .pipe()
        .subscribe(resp => {
          this.apiAnswer = resp;
          resolve(this.apiAnswer);
        } );
    }).then((apiAnswer)=>{
      if(this.apiAnswer.code == '100') {
        //this.router.navigateByUrl('/studentManage');
        Swal.fire({
          type: 'success',
          title: '修改成功',
          text: '修改了一条学生数据到数据库中',
          footer: '<a href>you are the best!</a>'
        })
      }else if(this.apiAnswer.code == '200'){
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: 'Something went wrong!',
          footer: '<a href>Why do I have this issue?</a>'
        })}
    },()=>{
      console.log("saveStudentPromise的inreject方法中");
    });
    // this.http.post<HttpResponse<any>>(this.updateUrl, body, httpOptions)
    //   .pipe()
    //   .subscribe(resp => {
    //     this.apiAnswer = resp;
    //     // const keys = resp.headers.keys();
    //     // this.harders = keys.map(
    //     //   key => this.apiAnswer = {...resp.body}
    //     // )
    //     console.log("update方法中updatestudent后返回的api:");
    //     console.log(this.apiAnswer);
    //     resolve(this.apiAnswer);
    //   } );
    return this.apiAnswer;
  }
  /*
  * 用post方法请求
  * */
  addStudent (student: Student) : Observable<HttpResponse<any>> {
    let body = JSON.stringify(student);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("poststudent:");
    console.log(student);
    return this.http.post<HttpResponse<any>>(this.addUrl, body, httpOptions);
  }
}

export class Student {
  constructor(
    public id: string,
    public name: string,
    public sex: string,
    public birthday: string,
    public email: string,
    public idCard: string,
    public phone: string,
    public address: string,
    public pinyin:string,
    public description:string,
    public classId:string,
    public password:string,
    public state:string,
    public picUrl:string
  ) { }
}
