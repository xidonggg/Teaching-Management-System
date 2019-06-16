import {Component, OnInit} from '@angular/core';
import {EnrollmentServiceService} from './modules/enrollmentManage/enrollment-service.service';
import {LocationStrategy} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor( private location:LocationStrategy) { }

  title = 'train';
  isform = false;
  ngOnInit(): void {
    // var storage  =  window.sessionStorage;
    // var index = storage.getItem("isform");
    // console.log("app中，isform的初始值为：");
    // console.log(index);
    // console.log(this.isform);
    // if(index != null && index == "true"){
    //   this.isform = true;
    // }
    // storage.setItem("isform", "false");
    // var index2 = storage.getItem("isform");
    // console.log("app中，isform修改后的值为：");
    // console.log(index2);
    // console.log(this.isform);
    console.log(location.pathname);
    console.log(location);
    if (location.pathname == "/entryForm") {
      this.isform = true;
    }
  }

}
