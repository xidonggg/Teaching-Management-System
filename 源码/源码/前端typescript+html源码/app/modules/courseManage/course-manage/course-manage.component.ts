import { Component, OnInit } from '@angular/core';
import {Course, CourseClassSelect, CourseServiceService} from '../course-service.service';
import {Router} from '@angular/router';
import {ClassServiceService} from '../../classManage/class-service.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-course-manage',
  templateUrl: './course-manage.component.html',
  styleUrls: ['./course-manage.component.css']
})
export class CourseManageComponent implements OnInit {

  apiAnswer: any;
  harders: any;
  private nodes = [];
  private courses: Course[] = [];
  private courseClass:CourseClassSelect[]=[];
  //--------------课程选择--------------
  listOfOption: Array<{ label: string; value: string; able: string}> = [];
  listOfTagOptions = [];
  constructor(private courseService: CourseServiceService, private router: Router, private classService: ClassServiceService) {
   // this.courses = courseService.getAllCourse();


  }

  deleteCourseClass(cs:string){
    this.courseService.deleteCourseClass(cs)
      .subscribe((resp)=>{
        // @ts-ignore
        if(resp.result.ret == '0'){
          Swal.fire({
            type: 'success',
            title: '成功',
          })
          this.ngOnInit();
        }else{
          Swal.fire({
            type: 'error',
            title: '失败',
          })
        }
      })

  }
  detail(course: Course){
    this.router.navigateByUrl("/courseDetail/"+course.id);
  }
  ngOnInit() {
    //获得选课结果
    this.courseService.getAllClassCourse()
      .subscribe(resp=>{
        this.courseClass = resp.body.list;
      })
    //获得班级树
    this.classService.getAllClassTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.nodes = this.apiAnswer.list;
      })
    //获得课程
    this.courseService.getAllCourse()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得了course:");
        this.courses = this.apiAnswer.list;
        console.log(this.apiAnswer);
        console.log(this.courses);
        //--------------课程下拉框初始化----------------
        const children: Array<{ label: string; value: string; able: string }> = [];

        for (let i = 0; i < this.courses.length; i++) {
          children.push({ label: this.courses[i].name+"-"+this.courses[i].teachers, value: this.courses[i].id, able: "false" });
        }
        this.listOfOption = children;
        //----------------------------------------
      })

  }
  //----------------选课-----------------------
  courseSelectionConfirm(){
    console.log(this.listOfTagOptions.toString());
    console.log(this.value);
    if(this.value.toString() == "" || this.listOfTagOptions.toString() == ""){
      Swal.fire({
        type: 'error',
        title: '有未选择的项',
      })
      return ;
    }
    this.courseService.courseSelect(this.value.toString(),this.listOfTagOptions.toString())
      .subscribe((resp)=>{
        console.log(resp);
        // @ts-ignore
        if(resp.result.ret == '0'){
          Swal.fire({
            type: 'success',
            title: '成功',
          })
          this.ngOnInit();
        }else{
          Swal.fire({
            type: 'error',
            title: '失败',
          })
        }
      });
  }
  //------------课程选择----------
  coursechange($event: string[]){
    console.log(this.listOfTagOptions);
  }
  //----------------班级选择---------------
  value: string[] = [];

  onChange($event: string[]): void {
    console.log($event);
    console.log(this.value);
    if(this.value != null && this.value.toString().match('^[a-z0-9]{32}$') == null){
      Swal.fire({
        type: 'error',
        title: '请选择班级',
      })
      this.value = [];
    }
  }
  create(){
    this.router.navigateByUrl('/courseForm/0');
  }
  update(course: Course){
    this.router.navigateByUrl('/courseForm/'+course.id);
  }
  delete(course: Course){
    console.log("进入了删除方法");
    console.log(course.id);
    this.courseService.deleteCourse(course.id);
    let aa = this.courses.indexOf(course);
    let cc = this.courses;
    this.courses = [];
    for(let i = 0; i < cc.length; i++){
      if(i != aa){
        this.courses.push(cc[i]);
      }
    }
    console.log(this.courses);
  }
}
