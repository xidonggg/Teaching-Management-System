import { Component, OnInit } from '@angular/core';
import {Clue, CommunicationRecord, EnrollmentPlan, EnrollmentServiceService} from '../enrollment-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from "sweetalert2";
import {FormBuilder, FormGroup} from '@angular/forms';
import {StaffManageService} from '../../staffManage/staff-manage.service';
import {format} from 'date-fns';

@Component({
  selector: 'app-clue-detail',
  templateUrl: './clue-detail.component.html',
  styleUrls: ['./clue-detail.component.css']
})
export class ClueDetailComponent implements OnInit {

  private newrecord:CommunicationRecord = new CommunicationRecord('','','','','');
  formModel: FormGroup;
  apiAnswer:any;
  private clue = new Clue('','','','','','',0,'',[],'','','',)
  private records:CommunicationRecord[] = [];
  private selectedValue:String;
  private communicationStyle:String;
  teacherslistOfOption: Array<{ label: string; value: string }> = [];
  teacherslistOfTagOptions = [];
  teachersname: string[];
  inputValue:string;
  constructor(private enrollmentService:EnrollmentServiceService, private router: Router, private routeInfo: ActivatedRoute,
              private staffService:StaffManageService) { }

  ngOnInit() {
    //---------初始化表单-------------
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
      }
    )

    let clueId : string = this.routeInfo.snapshot.params.id;
    this.enrollmentService.getClueById(clueId)
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log(this.apiAnswer);
        if(this.apiAnswer.result.ret == '0'){
          console.log("你要的答案");
          this.clue = this.apiAnswer.data;
          // @ts-ignore
          this.records = this.clue.communicationRecords;
          console.log(this.records);
          // @ts-ignore
          this.selectedValue = this.clue.state;
        }else{
          Swal.fire({
            type: 'error',
            title: 'Oops...',
          })
        }
      });

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

  }
  back(){
    this.router.navigateByUrl("/recruitStudentsManage");
  }
  statechange(clue:Clue,state:String){
    console.log(this.selectedValue);
    // @ts-ignore
    this.enrollmentService.changeClueState(clue.id,state);
  }
  onChange(result: Date): void {
    console.log('Selected Time: ', result);
  }

  onOk(result: Date): void {
    console.log('onOk', result);
    // @ts-ignore
    this.newrecord.communicateTime = format(result,'YYYY-MM-DD HH:mm:ss');
  }
  communicationchange(){
    console.log(this.communicationStyle);
  }

  save(){
    let temp = "";
    for(let i = 0; i <this.teacherslistOfTagOptions.length; i++){
      temp += this.teacherslistOfTagOptions[i] + ",";
    }
    temp = temp.substr(0,temp.length-1);
    // @ts-ignore
    this.newrecord.contentPsersonName = temp;
    // @ts-ignore
    this.newrecord.style = this.communicationStyle;
    // @ts-ignore
    this.newrecord.content = this.inputValue;
    this.records.push(this.newrecord);
    // @ts-ignore
    this.clue.communicationRecords = this.records;
    this.enrollmentService.updateClue(this.clue);
  }
}
