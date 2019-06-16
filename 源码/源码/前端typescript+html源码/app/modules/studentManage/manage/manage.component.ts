import { Component, OnInit } from '@angular/core';
import {Student, StudentServiceService} from '../student-service.service';
import {Config, HttpConnectServiceService} from '../../../tools/http-connect-service.service';
import {Router} from '@angular/router';
import {NzFormatEmitEvent} from 'ng-zorro-antd';
import {Class, ClassServiceService} from '../../classManage/class-service.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {

  private nodes;
  private students: Array<Student>;
  apiAnswer: any;
  harders: any;
  private avalibaleClasses:Class[] = [];
  private student: Student = new Student('1', 'yanbin', '女', '1997-9-8', '123@123.com', '330716555678987655', '3453535534','bghfghfghfghfghfghfghfgh','','','','','');

  constructor(private httpService: HttpConnectServiceService, private studentService: StudentServiceService, private router: Router
  ,private classService:ClassServiceService) { }

  ngOnInit() {
    this.studentService.getAllStudents()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.students = this.apiAnswer.extend.list.personList;
        this.selectedValue = [];
        for(let i = 0; i < this.students.length; i++){
          this.selectedValue.push(this.students[i].classId);
        }
      })
    this.getClassTreeInit();
    this.getAvaliableClassInit();
  }

  getClassTreeInit(){
    //获得班级树
    this.classService.getAllClassTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.nodes = this.apiAnswer.list;
      })
  }
  selectedValue:string[] = [];
  getAvaliableClassInit(){
    this.studentService.getAvabileClass()
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.avalibaleClasses = resp.body.list;
        console.log(this.avalibaleClasses);
      })
  }
  stuClassChange(stu:Student,i:number){
    console.log(this.selectedValue.toString())
    console.log(this.selectedValue[i])
    this.studentService.updateStuClass(this.selectedValue[i],stu.id)
      .subscribe(resp=>{
        // @ts-ignore
        if(resp.result.ret == '0'){
          Swal.fire({
            type: 'success',
            text: '成功',
          })
          this.ngOnInit();
        }
      })
  }
  uploadStudents() {
    this.router.navigateByUrl('/uploadStudents');
  }
  create() {
    this.router.navigateByUrl('/studentForm/0');
  }
  upadte(student: Student) {
    this.router.navigateByUrl('/studentForm/' + student.id);
  }
  delete(student: Student) {
    this.studentService.deleteStudent(student.id.toString());
    window.location.reload();
  }
  //-----------点击树--------------
  nzevent(){
    //获取未分配学生
    this.studentService.getStudentByclassId("0000")
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.students = resp.list;
        this.selectedValue = [];
        for(let i = 0; i < this.students.length; i++){
          this.selectedValue.push(this.students[i].classId);
        }
      })
  }
  nzEvent(event: NzFormatEmitEvent): void {
    //根据班级查找学生
    console.log(event.keys);
    this.studentService.getStudentByclassId(event.keys.toString())
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.students = resp.list;
        this.selectedValue = [];
        for(let i = 0; i < this.students.length; i++){
          this.selectedValue.push(this.students[i].classId);
        }
      })
  }
//--------------获取可选择的班级-----------------

}
