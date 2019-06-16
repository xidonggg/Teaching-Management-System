import { Component, OnInit } from '@angular/core';
import {Class, ClassServiceService, RemoteSearchClass} from '../class-service.service';
import {NzFormatEmitEvent} from 'ng-zorro-antd';
import {FieldServiceService, Org} from '../../organizationManage/organization-manage/field/field-service.service';
import {FormControl} from '@angular/forms';
import {debounceTime} from 'rxjs/operators';
import {Router} from '@angular/router';
import {format} from 'date-fns';
import Swal from "sweetalert2";

@Component({
  selector: 'app-class-manage',
  templateUrl: './class-manage.component.html',
  styleUrls: ['./class-manage.component.css']
})
export class ClassManageComponent implements OnInit {

  harders:any;
  apiAnswer:any;
  orgs: Org[];

  private isendselectedValue = '';
  private schoolseletedValue = '';
  private yearselectedValue = '';
  private classes: Class[] = [];
  private nameFilter: FormControl = new FormControl();
  private keyWord = "";
  date = null;
  //指导教师用浮动框全部显示
  private teachers :string[] = [];

  constructor(private classService: ClassServiceService, private fieldService: FieldServiceService, private router:Router) {
    //this.classes = classService.getAllClass();
  }

  ngOnInit() {
    //初始化班级
    this.classService.getAllClass()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.classes = this.apiAnswer.list;

        //教师气泡展示
        let num = 0
        for(let i of this.classes){
          let index = i.directors.indexOf(',');
          if(index == -1)
            index = i.directors.length;
          this.teachers[num] = i.directors.substr(0,index);
          num++;
        }
      })

    //初始化校区
    this.fieldService.getAllSchools()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.orgs = this.apiAnswer.extend.list.getschoolList;
        console.log("this.orgs");
        console.log(this.orgs);
      })
    //根据班级名称搜索过滤
    this.nameFilter.valueChanges
      .pipe(debounceTime(500))
      .subscribe(
        value => {
          return this.getKeyWord(value);
        }
      );
  }
  getKeyWord(value: string) {
    this.keyWord = value;
    // console.log(value);
  }
  search(){
    let searchClass: RemoteSearchClass = new RemoteSearchClass(this.isendselectedValue,this.schoolseletedValue,this.yearselectedValue,this.keyWord);
    this.classService.searchClass(searchClass)
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.classes = resp.list;
      });
  }
  add(){
    this.router.navigateByUrl('/newclass/0');
  }
  update(myclass: Class){
    this.router.navigateByUrl('/newclass/'+myclass.id);
  }
  delete(myclass: Class){
    this.classService.deleteClass(myclass.id)
      .subscribe(resp => {
        console.log("resp");
        console.log(resp);
        // @ts-ignore
        if(resp.result.ret == "0"){
          Swal.fire({
            type: 'success',
            title: '删除成功'
          })
          this.ngOnInit();
        }else{
          Swal.fire({
            type: 'error',
            title: this.apiAnswer.result.msg
          })
        }
      } );
  }
  isendchange(value:string){
    console.log(value);
    this.isendselectedValue = value;
  }
  schoolchange(value: string){
    console.log(value);
    this.schoolseletedValue = value;
  }
  yearchange(result: Date){
    console.log(result);
    this.yearselectedValue = format(result,'YYYY');
    console.log("选择的日期：");
    console.log(this.yearselectedValue);
  }
}
