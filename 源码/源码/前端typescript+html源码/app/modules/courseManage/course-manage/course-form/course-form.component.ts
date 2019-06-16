import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Course, courseArrange, CourseServiceService} from '../../course-service.service';
import {StaffManageService} from '../../../staffManage/staff-manage.service';
import {Field, FieldServiceService} from '../../../organizationManage/organization-manage/field/field-service.service';
import {ActivatedRoute, Data, Router} from '@angular/router';
import {format} from 'date-fns';
import Swal from "sweetalert2";
import {Schedule, ScheduleServiceService} from '../../../organizationManage/organization-manage/schedule/schedule-service.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  scheduleOption:string[] = [];
  //表单
  formModel: FormGroup;
  private course: Course = new Course("","","","","","","","",0,[]);
 //下拉教师
  apiAnswer: any;
  teachersname: string[];
  teacherslistOfOption: Array<{ label: string; value: string }> = [];
  teacherslistOfOption2: Array<{ label: string; value: string }> = [];
  private isconfirm = false;
  teacherslistOfTagOptions = [];
  teacherconfirm(){
    for (let i of this.teacherslistOfTagOptions) {
      this.teacherslistOfOption2.push({ label: i, value:i });
    }
    console.log(this.teacherslistOfOption2);
    this.isconfirm = true;
  }
//下拉选择地址
  fields: Field[]
  classrlistOfOption: Array<{ label: string; value: string }> = [];
  classrlistOfTagOptions = [];

  num:number = 1;
  arrange: any = [{"id":1,"date":"","selectedTime":"", "address":"", "teachers":[]}]
  newaddInput(){
    console.log("添加了：");
    console.log(this.arrange);
    this.num = this.arrange.length + 1;
    let number = this.arrange.length +1;
    this.arrange.push({"id":number,"date":"","selectedTime":"","address":"","teachers":[]})
    console.log(this.arrange);
  }
  newremoveInput(item){
    console.log(item);
    let a = this.arrange.indexOf(item);
    console.log(a);
    this.arrange.splice(a,1);
    for(let k = a; k < this.arrange.length; k++){
      this.arrange[k].id--;
    }
  }
  ondateChange(result: Data, item: any){
    let a = this.arrange.indexOf(item);
    console.log(a);
    this.arrange[a].date = format(result.toString(), 'YYYY-MM-DD');
  }
  constructor(private staffService: StaffManageService, private fieldService: FieldServiceService,
              private router: Router, private courseService: CourseServiceService, private routeInfo: ActivatedRoute,
              private scheduleService:ScheduleServiceService) { }

  ngOnInit() {
    let courseId : string = this.routeInfo.snapshot.params.id;
    if(courseId != "0"){
      this.courseService.getCourseById(courseId)
        .subscribe(resp => {
          const keys = resp.headers.keys();
          keys.map(
            key => this.apiAnswer = {...resp.body}
          )
          console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
          console.log(this.apiAnswer);
          if(this.apiAnswer.result.ret == '0'){
            this.course = this.apiAnswer.data;
            console.log(this.course);
          }else{
            Swal.fire({
              type: 'error',
              title: 'Oops...',
            })
          }
          this.formModel.reset({
            name: this.course.name,
            number: this.course.number,
            describe: this.course.describe
          })
        });
    }

    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
        number: [''],
        describe: ['']
      }
    );
    this.scheduleService.getAllSchedule()
      .subscribe(resp=>{
        let schedules:Schedule[]  =resp.body.list;
        schedules.forEach((item)=>{
          this.scheduleOption.push(item.startTime+"-"+item.endTime);
        })
        console.log("时间段：");
        console.log(resp);
        console.log(this.scheduleOption);
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


    //地址初始化
    this.fieldService.getAllFields()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("22222222222222");
        console.log(this.apiAnswer);
        this.fields = this.apiAnswer.extend.list.schoolYieldList;
        console.log(this.fields);

        const classrchildren: Array<{ label: string; value: string }> = [];
        for (let i of this.fields) {
          // @ts-ignore
          classrchildren.push({ label: i.school+"/"+i.name, value:i.school+"/"+i.name });
        }
        this.classrlistOfOption = classrchildren;
      })

  }

  save(){
    this.course = this.formModel.value;
    //存教师
    let temp = "";
    for(let i = 0; i <this.teacherslistOfTagOptions.length; i++){
      temp += this.teacherslistOfTagOptions[i] + ",";
    }
    temp = temp.substr(0,temp.length-1);
    this.course.teachers = temp;
    //存教室
    let temp2 = "";
    for(let i = 0; i <this.classrlistOfTagOptions.length; i++){
      temp2 += this.classrlistOfTagOptions[i] + ",";
    }
    temp2 = temp2.substr(0,temp2.length-1);
    this.course.classrooms = temp2;

    let tt:Array<courseArrange> = []
    let tempstring = "";
    console.log(this.arrange);
    for(let i of this.arrange){
      for(let j = 0; j < i.teachers.length; j++){
        tempstring += i.teachers[j] + ",";
      }
      tempstring = tempstring.substr(0,tempstring.length-1);
      let myarrange: courseArrange = new courseArrange(null,i.date,i.selectedTime,tempstring,i.address);
      tt.push(myarrange);
      tempstring = "";
    }
    this.course.courseArrange = tt;
    this.course.teachTimes = this.arrange.length;

    console.log("最后保存的结果为：");
    console.log(this.course);
    this.courseService.addCourse(this.course)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        if(this.apiAnswer.result.ret == '0'){
          Swal.fire({
            type: 'success',
            title: '添加成功',
          })
          this.router.navigateByUrl("/courseManage");
        }else{
          Swal.fire({
            type: 'error',
            title: '添加失败',
          })}
      } );;
  }
  cancel(){
    this.router.navigateByUrl("/courseManage");
  }
}
