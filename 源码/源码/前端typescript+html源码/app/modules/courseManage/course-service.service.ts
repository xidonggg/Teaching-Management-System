import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import Swal from "sweetalert2";
import {Student} from '../studentManage/student-service.service';
import {Class} from '../classManage/class-service.service';

@Injectable({
  providedIn: 'root'
})
export class CourseServiceService {

  private getAllCourseUrl = "/springMVC-spring-hibernate/course/list";
  private saveCourseUrl = "/springMVC-spring-hibernate/course/save";
  private updateCourseUrl = "/springMVC-spring-hibernate/course/update?_method=PUT";
  private deleteCourseUrl = "/springMVC-spring-hibernate/course/delete?_method=DELETE";
  private getCourseByIdUrl = "springMVC-spring-hibernate/course/get/";
  private courseSelectUrl = "/springMVC-spring-hibernate/course/courseClassSelect";
  private getCourseClass = "/springMVC-spring-hibernate/course/getCourseClass";
  private deleteCourseClassUrl = "/springMVC-spring-hibernate/course/deleteCourseClassSelect";
  apiAnswer: any;
  private course: Course = new Course("fsdfsd","体育课","ty1","大师傅似的","ad,fsdf,f","fsd,sdf,fds","gffg","123-213-32",2,[
    new courseArrange("gfdg","2019-5-14 12:53:00","2019-5-14 14:53:00","432,23,342","4234"),
    new courseArrange("errt","2019-5-15 12:53:00","2019-5-15 13:00:00","432,23,342","4234")
  ])
  token:string="";
  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }

  courseSelect(classId:string,courseIds:string):Observable<HttpResponse<any>>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    return this.http.post<HttpResponse<any>>(this.courseSelectUrl, "classId="+ classId+"&courseIds="+courseIds, httpOptions)
  }
  getAllCourse(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllCourseUrl, {observe: 'response'}
    )
  };
  getAllClassCourse(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getCourseClass, {observe: 'response'}
    )
  };
  getCourseById (id: string): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getCourseByIdUrl + id, {observe: 'response'}
    )
  };

  deleteCourse(id : string){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    console.log("进入删除方法了，id:");
    console.log(id);
    this.http.post<HttpResponse<any>>(this.deleteCourseUrl, "id="+ id, httpOptions)
      .pipe()
      .subscribe(resp => {
        console.log(this.apiAnswer);
        this.apiAnswer = resp;
        if(this.apiAnswer.result.ret == '0'){
          Swal.fire({
            type: 'success',
            title: '删除成功',
          })
        }else{
          Swal.fire({
            type: 'error',
            title: '删除失败',
          })}
      } );
  }

  addCourse (course: Course):Observable<HttpResponse<any>>  {
    let body = JSON.stringify(course);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log(body);
    return this.http.post<HttpResponse<any>>(this.saveCourseUrl, body, httpOptions)
  }
  deleteCourseClass(id:string):Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    return this.http.post<HttpResponse<any>>(this.deleteCourseClassUrl, "id="+ id, httpOptions)
  }

}

export class Course{
  constructor(
    public id: string,
    public name: string,
    public number: string,
    public describe: string,
    public teachers: string,
    public classrooms: string,
    public establishPerson: string,
    public establishTime: string,
    public teachTimes: number,
    public courseArrange: courseArrange[]
  ){}
}

export class courseArrange{
  constructor(
    public id:string,
    public date:string,
    public timeSlot:string,
    public teachers:string,
    public classroom:string
  ){}
}
export class CourseClassSelect{
  constructor(
    public id:string,
    public createPerson:string,
    public createTime:string,
    public classId:string,
    public courseId:string,
    public myclass:Class,
    public course:Course
  ){}
}
