import { Component, OnInit } from '@angular/core';
import {Class, ClassServiceService} from '../../classManage/class-service.service';
import {Course, CourseClassSelect} from '../../courseManage/course-service.service';
import {AchievementServiceService} from '../achievement-service.service';
import Swal from "sweetalert2";
import {Router} from '@angular/router';

@Component({
  selector: 'app-achievement-manage',
  templateUrl: './achievement-manage.component.html',
  styleUrls: ['./achievement-manage.component.css']
})
export class AchievementManageComponent implements OnInit {

  constructor(private achievementService:AchievementServiceService,private classService:ClassServiceService,private router:Router) { }
  selectedValue = '';
  private nodes = [];
  private teachernodes = [];
  private myclassed:Class[] = [];//放我的班级
  private courseclasss:CourseClassSelect[] = [];//存放显示的课程


  ngOnInit() {
    this.myclassInit();
    this.teachernodesInit();
    this.getMyCourse();
  }
  myclassInit(){
    this.achievementService.getMyClasses()
      .subscribe((resp)=>{
        console.log(resp);
        // @ts-ignore
        this.nodes = resp.body.list;
        console.log(this.nodes);
      })
  }
  private muCourses:Array<Course> = [];
  getMyCourse(){
    this.achievementService.getMyCourse()
      .subscribe((resp)=>{
        console.log(resp);
        // @ts-ignore
        this.muCourses = resp.body.list;
        console.log(this.muCourses);
      })
  }
  teachernodesInit(){
    this.achievementService.getMyTeachClass()
      .subscribe((resp)=>{
        console.log(resp);
        // @ts-ignore
        this.teachernodes = resp.body.list;
        console.log(this.teachernodes);
      })
  }
  courseInit(){
    this.achievementService.getMyCourseClass(this.value.toString())
      .subscribe((resp)=>{
        // @ts-ignore
        this.courseclasss = resp.list;
      })
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
    }else{
      this.courseInit();
    }
  }

  detail(cs:CourseClassSelect){
    console.log("wtf");
    console.log(cs);
    this.router.navigateByUrl("/AchievementDetail/"+cs.myclass.id+"&"+cs.course.id);
  }
}
