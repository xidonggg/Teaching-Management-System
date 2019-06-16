import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import Swal from "sweetalert2";
import {Student} from '../../modules/studentManage/student-service.service';
import {Router} from '@angular/router';
import {MenuComponent} from '../../composition/menu/menu.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private loginUrl = "/springMVC-spring-hibernate/userlogin/login";
  apiAnswer: any;
  teacherId = "";
  password = "";
  message = "";
  @Output('islogin')
  islogin : EventEmitter<boolean> = new EventEmitter();
  constructor(public http: HttpClient,private router:Router) { }

  ngOnInit() {
    console.log("login的ngInit")
  }
  doOnInput(value: any){
    // @ts-ignore
    this.teacherId = value.target.value;
  }
  doOnInput2(value: any){
    // @ts-ignore
    this.password = value.target.value;
  }
  // login(){
  //   console.log("用户名：");
  //   console.log(this.teacherId);
  //   console.log("密码：");
  //   console.log(this.password);
  //   this.islogin.emit(true);
  //
  //   var storage  =  window.sessionStorage;
  //   storage.setItem("authority","查看学生信息,查看班级信息");
  //   var index = storage.getItem("authority");
  //   console.log("index:");
  //   console.log(index);
  // }
  login (): any {
    console.log("login的login")
    let userlogin : UserLogin = new UserLogin(null, this.teacherId,this.password, null);
    let body = JSON.stringify(userlogin);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("userlogin:");
    console.log(body);
    this.http.post<HttpResponse<any>>(this.loginUrl, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        this.apiAnswer = resp;
        if(this.apiAnswer.result.ret == '0'){
          console.log(this.apiAnswer);
          this.islogin.emit(true);//登陆
          var storage  =  window.localStorage;
          storage.setItem("authority",this.apiAnswer.data.authorities);
          storage.setItem("token", this.apiAnswer.token);
          storage.setItem("pinyin",this.teacherId)
          var index = storage.getItem("authority");
          var token = storage.getItem("token");
          var pinyin = storage.getItem("pinyin");
          console.log("index:");
          console.log(index);
          console.log("token:");
          console.log(token);
          console.log("pinyin:");
          console.log(pinyin);
          window.location.reload();
         // this.menuComponent.ngOnInit();
          // this.router.navigateByUrl("/index/homePage");
        }else if(this.apiAnswer.result.ret == '-1'){
          this.message = this.apiAnswer.result.msg;
          }
      } );
    return this.apiAnswer;
  }
}

export class UserLogin{
  constructor(
    public id: string,
    public pinyin: string,
    public password: string,
    public authorities: string,
  ) { }
}
