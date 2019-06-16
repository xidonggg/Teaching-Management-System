import {Component, OnInit} from '@angular/core';
import {Class, ClassServiceService} from '../../class-service.service';
import {ActivatedRoute, Router} from '@angular/router';

import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FieldServiceService, Org} from '../../../organizationManage/organization-manage/field/field-service.service';
import {debounceTime} from 'rxjs/operators';
import {StaffManageService} from '../../../staffManage/staff-manage.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-class-form',
  templateUrl: './class-form.component.html',
  styleUrls: ['./class-form.component.css']
})
export class ClassFormComponent implements OnInit {

  orgs: Org[];
  class: Class = new Class('0', '', '', '', '', '', true, 0);
  private apiAnswer: any;// http通信中使用
  private harders: any;//http通信中使用
  formModel: FormGroup;
  private classId;
  private schoolseletedValue = '';
  private yearselectedValue = '';
  private fieldselectedValue = '';
  private isendselectedValue = '否';
  private dates: number[] = [];
  private fields: string[] = [];

 //指导教师
  teachersname: string[];
  teacherslistOfOption: Array<{ label: string; value: string }> = [];
  teacherslistOfTagOptions = [];


  constructor(private classService: ClassServiceService,private fieldService :FieldServiceService,
              private router: Router, private activiteRouter: ActivatedRoute,
              private staffService: StaffManageService) {
  }

  ngOnInit() {
    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: ['', [Validators.required, Validators.minLength(1)]],
        totalTime: [''],
        // directors: [''],
      }
    );
    //获取时间
    let date2 = new Date().getFullYear();
    console.log("时间：");
    console.log(date2+1);

    for(let i = 0; i < 25;i++){
      this.dates[i] = i+2000;
    }

    //获取校区
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

    //教师初始化
    this.staffService.getStaffNames()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("111111111111111");
        console.log(this.apiAnswer);
        this.teachersname = this.apiAnswer.list;
        console.log(this.teachersname);

        const teacherchildren: Array<{ label: string; value: string }> = [];
        for (let i of this.teachersname) {
          teacherchildren.push({ label: i, value:i });
        }
        this.teacherslistOfOption = teacherchildren;
      })

    //获取教室
    this.fieldService.getAllFields()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.fields = this.apiAnswer.extend.list.schoolYieldList;
        console.log("this.fields");
        console.log(this.fields);
      })

    let classId: string = this.activiteRouter.snapshot.params.id;
    this.classId = classId;
    if (classId == '0') {

    } else {
      //异步根据id从服务器获取学生信息，获取后重置formModel
      this.fieldselectedValue = this.class.teachplace;
      this.yearselectedValue = this.class.startyear;
      this.schoolseletedValue = this.class.school;
      if(this.isendselectedValue == "是")
        this.class.isend = true;
      else
        this.class.isend = false;
      console.log("this.class");
      console.log(this.class);
      this.formModel.reset({
        name: this.class.name,
        totalTime: this.class.totalTime,
        // directors: this.class.directors
      });
    }

  }

  /**
   * 取消，路由到学生信息管理主页面
   */
  cancel() {
//    Swal.fire('Hello world!');
    this.router.navigateByUrl('/classManage');
  }

  /*
  * 保存，根据id判断是新建还是更新，注意这里 返回一下处理结果
  * */
  save() {
    if (this.classId == '0') {
      this.class = this.formModel.value;
      this.class.school = this.schoolseletedValue;
      this.class.startyear = this.yearselectedValue;
      this.class.teachplace = this.fieldselectedValue;
      this.class.isend = false;
      //存教师
      let temp = "";
      for(let i = 0; i <this.teacherslistOfTagOptions.length; i++){
        temp += this.teacherslistOfTagOptions[i] + ",";
      }
      temp = temp.substr(0,temp.length-1);
      this.class.directors = temp;
      this.classService.addClass(this.class)
        .subscribe(resp => {
          this.apiAnswer = resp;
          console.log("说好的100会返回")
          if(this.apiAnswer.result.ret == '0'){
            Swal.fire({
              type: 'success',
              title: '添加成功',
            })
            this.router.navigateByUrl("/classManage")
          }else{
            Swal.fire({
              type: 'error',
              title: 'Oops...',
            })}
        } );;

    } else {
      this.class = this.formModel.value;
      this.class.id = this.classId;
      this.class.school = this.schoolseletedValue;
      this.class.startyear = this.yearselectedValue;
      this.class.teachplace = this.fieldselectedValue;
      if(this.isendselectedValue == "是")
        this.class.isend = true;
      else
        this.class.isend = false;
      //存教师
      let temp = "";
      for(let i = 0; i <this.teacherslistOfTagOptions.length; i++){
        temp += this.teacherslistOfTagOptions[i] + ",";
      }
      temp = temp.substr(0,temp.length-2);
      this.class.directors = temp;
      this.classService.updateClass();
    }

  }

  schoolchange(value: string){
    console.log(value);
    this.schoolseletedValue = value;
  }
  yearchange(value: string){
    console.log(value);
    this.yearselectedValue = value;
  }
  fieldchange(value: string){
    console.log(value);
    this.fieldselectedValue = value;
  }
  isendchange(value:string){
    console.log(value);
    this.isendselectedValue = value;
  }
}
