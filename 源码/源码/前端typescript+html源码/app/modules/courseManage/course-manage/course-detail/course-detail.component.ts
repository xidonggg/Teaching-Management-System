import { Component, OnInit } from '@angular/core';
import {Course, CourseServiceService} from '../../course-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from "sweetalert2";

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {

  private course: Course = new Course("","","","","","","","",0,[]);
  private courseArrange = [];
  private arrangeTime: number[] = [];
  apiAnswer: any;
  constructor(private courseService: CourseServiceService, private router: Router, private routeInfo: ActivatedRoute) { }

  ngOnInit() {
    let courseId : string = this.routeInfo.snapshot.params.id;
    //this.course = this.courseService.getCourse();
    this.courseService.getCourseById(courseId)
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log(this.apiAnswer);
        if(this.apiAnswer.result.ret == '0'){
          this.course = this.apiAnswer.data;
          this.courseArrange = this.course.courseArrange;
          console.log(this.course);
        }else{
          Swal.fire({
            type: 'error',
            title: 'Oops...',
          })
        }
      });
  }

  back(){
    this.router.navigateByUrl("/courseManage");
  }
}
