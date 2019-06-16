import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Course, CourseServiceService} from '../../courseManage/course-service.service';
import {Class, ClassServiceService} from '../../classManage/class-service.service';
import {Student, StudentServiceService} from '../../studentManage/student-service.service';

@Component({
  selector: 'app-achievement-detail',
  templateUrl: './achievement-detail.component.html',
  styleUrls: ['./achievement-detail.component.css']
})
export class AchievementDetailComponent implements OnInit {

  constructor(private router: Router, private routeInfo: ActivatedRoute,
              private classService:ClassServiceService,private courseService:CourseServiceService,
              private studentService:StudentServiceService) { }
  private course:Course = new Course('','','','','','','','',0,[]);
  private myclass:Class = new Class('','','','','','',false,0);
  private students:Array<Student>=[];
  private ids:string[] = [];
  private classId="";
  private courseId="";
  private isSaved = false;

  ngOnInit() {
    let csId : string = this.routeInfo.snapshot.params.id;
    // let csId2 : string = this.routeInfo.snapshot.params.id2;
    // this.classId = csId1;
    // this.courseId = csId2;
    // console.log("查询参数：",csId1);
    // console.log("查询参数：",csId2);
    console.log("chengji:"+csId);
    this.ids = csId.split("&");
    this.getCLassInfo();
    this.getCourseInfo();
    this.getStudentsInfo();
  }

  getCLassInfo(){
    console.log(this.ids[0]);
    this.classService.getClassById(this.ids[0])
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.myclass = resp.data;
        console.log(this.myclass);
      })
  }
  getCourseInfo(){
    console.log(this.ids[1]);
    this.courseService.getCourseById(this.ids[1])
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.course = resp.body.data;
        console.log(this.course)
      })
  }

  getStudentsInfo(){
    this.studentService.getStudentByclassId(this.ids[0])
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.students = resp.list;
      })
  }
  cancel(){
    this.router.navigateByUrl("/achievementManage");
  }
  save(){
    this.isSaved = true;
  }
}
