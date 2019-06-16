import { Component, OnInit } from '@angular/core';
import {Clue, EnrollmentPlan, EnrollmentServiceService} from '../enrollment-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ClassServiceService} from '../../classManage/class-service.service';
import {Course, courseArrange, CourseServiceService} from '../../courseManage/course-service.service';
import {Data, Router} from '@angular/router';
import {format} from "date-fns";
import Swal from "sweetalert2";

@Component({
  selector: 'app-enrollment-plan',
  templateUrl: './enrollment-plan.component.html',
  styleUrls: ['./enrollment-plan.component.css']
})
export class EnrollmentPlanComponent implements OnInit {
  //表单
  formModel: FormGroup;
  private nodes = [];
  private courses: Course[] = [];
  apiAnswer:any;
  private plans:EnrollmentPlan[] = [new EnrollmentPlan('','','',[],'','',[],
    '','','','','','','','','',
    0,0,[])];
  private clues:Clue[] = [];
  selectedState:String[] = [];
  private plan:EnrollmentPlan =new EnrollmentPlan('','','',[],'','',[],
    '','','','','','','','','',
    0,0,[]);
  constructor(private enrollmentService:EnrollmentServiceService,private classService:ClassServiceService, private courseService:CourseServiceService,private router: Router) {
  }

  ngOnInit() {
    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
        communicateStaffname: [''],
        communicateStaffPhone:[''],
        planNumber:[''],
        describe: ['']
      }
    );
    //获得班级树
    this.classService.getAllClassTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.nodes = this.apiAnswer.list;
      })
    //获得招生计划
    this.enrollmentService.getAllPlans()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的计划");
        console.log(this.apiAnswer);
        this.plans = this.apiAnswer.list;
        for(let i= 0; i < this.plans.length; i++){
          // @ts-ignore
          this.selectedState[i] = this.plans[i].state;
        }
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
        for(let item of this.courses){
          children.push({ label: item.name, value: item.id, able: "false" });
        }
        // for (let i = 10; i < 36; i++) {
        //   children.push({ label: i.toString(36) + i, value: i.toString(36) + i, able: "false" });
        // }
        this.listOfOption = children;
        //----------------------------------------
      })


    //获得线索
    this.enrollmentService.getAllClues()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的线索");
        console.log(this.apiAnswer);
        this.clues = this.apiAnswer.list;
        console.log(this.clues);
      })
  }
  //------------班级选择-------------
  value: string[] = [];

  onChange($event: string[]): void {
    console.log($event);
    console.log(this.value);
  }
  //--------------课程选择--------------


  listOfOption: Array<{ label: string; value: string; able: string}> = [];
  listOfTagOptions = [];
  coursechange($event: string[]){
    console.log(this.listOfTagOptions);
  }
  //------------日期选择------------------
  private startTime:string;
  private endTime:string;
  onstartTimeChange(result: Date){
    console.log(result);
    this.startTime =  format(result, 'YYYY-MM-DD HH:mm:ss');
    console.log(this.startTime);
  }
  onstartTimeOk(result: Date){
    console.log(result);
    this.startTime = format(result,'YYYY-MM-DD HH:mm:ss');
    console.log(this.startTime);
  }
  onendTimeChange(result: Date){
    console.log(result);
    this.endTime =  format(result, 'YYYY-MM-DD HH:mm:ss');
    console.log(this.endTime);
  }
  onendTimeOk(result: Date){
    console.log(result);
    this.endTime = format(result,'YYYY-MM-DD HH:mm:ss');
    console.log(this.endTime);
  }
  statechange(plan:EnrollmentPlan,state:String){
    console.log(this.selectedState);

    console.log("this.plan");
    console.log(plan);
    // @ts-ignore
    this.enrollmentService.changePlanState(plan.id,state);
  }
  //-------------取消和保存--------------
  cancel(){
    this.router.navigateByUrl("/recruitStudentsManage");
  }
  save(){
    this.plan = this.formModel.value;
    //存教室
   // @ts-ignore
    this.plan.myclasses = this.value.toString();
   // @ts-ignore
    this.plan.courses = this.listOfTagOptions.toString();
    // @ts-ignore
    this.plan.startTime = this.startTime;
    // @ts-ignore
    this.plan.endTime = this.endTime;
    console.log("保存的plan信息为：");
    console.log(this.plan);
    this.enrollmentService.addPlan(this.plan)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log('说好的100会返回');
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '添加成功',
          });
          this.ngOnInit();
        } else {
          Swal.fire({
            type: 'error',
            title: '添加失败',
          });
        }
      });;
  }

  plandetail(plan:EnrollmentPlan){
    // @ts-ignore
    this.router.navigateByUrl("/recruitStudentDetail/"+plan.id);
  }

  cluedetail(clue:Clue){
    // @ts-ignore
    this.router.navigateByUrl("/clueDetail/"+clue.id);
  }
}
