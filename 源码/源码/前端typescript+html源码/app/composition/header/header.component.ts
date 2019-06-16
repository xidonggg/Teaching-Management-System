import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from '../../modules/studentManage/student-service.service';
import {Staff} from '../../modules/staffManage/staff-manage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private logOutUrl = "/springMVC-spring-hibernate/userlogin/loginout";
//  private getMyMessage = "/springMVC-spring-hibernate/userlogin/getUser"
  private pinyin:string = "";
  private picUrl:string = "";
  private name:string = "";
  private token = "";
  constructor(public http: HttpClient) { }

  ngOnInit() {
    this.myMessage();
    // setTimeout(()=>{
    //   var storage  =  window.localStorage;
    //   let pp: string = storage.getItem("pinyin");
    //   this.pinyin = pp;
    // },0)
  }
  // getStaffNames(): Observable<HttpResponse<any>> {
  //   return this.http.get<any>(
  //     this.getStaffNameUrl, {observe: 'response'}
  //   )
  // };
  signOut(){
    var storage  =  window.localStorage;
    let token: string = storage.getItem("token");
    // let pp: string = storage.getItem("pinyin");
    // this.pinyin = pp;
    console.log(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':token
      })
    }
    this.http.get<HttpResponse<any>>(this.logOutUrl, httpOptions)
      .pipe()
      .subscribe(resp => {
        storage.clear();
        window.location.reload();
      } );
  };
  myMessage(){
    let getUserUrl = "";
    var storage = window.localStorage;
    var pinyin = storage.getItem("pinyin");
    let token: string = storage.getItem("token");
    if(token != null && pinyin != null){
      if(pinyin.startsWith("stu")){
        this.getStuMessnge();
      }else{
        this.getStaffMessnge()
      }


    }
  }
  getStaffMessage = "/springMVC-spring-hibernate/staff/getStaffByPinyin";
  getStaffMessnge(){
    var storage = window.localStorage;
    let token: string = storage.getItem("token");
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':token
      })
    }
    console.log(token);
    this.http.get<HttpResponse<any>>(this.getStaffMessage, httpOptions)
      .pipe()
      .subscribe(resp => {
          // @ts-ignore
        let getStaffMessage:Staff = resp.data;
          this.name = getStaffMessage.name;
          this.picUrl = getStaffMessage.picUrl;
      } );
  }
  getStuMessage = "/springMVC-spring-hibernate/person/getPersonByPinyin";
  getStuMessnge(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    this.http.get<HttpResponse<any>>(this.getStuMessage, httpOptions)
      .pipe()
      .subscribe(resp => {
        console.log(resp);
        // @ts-ignore
        let getStuMessage:Student = resp.data;
        this.name = getStuMessage.name;
        this.picUrl = getStuMessage.picUrl;
      } );
  }
}
