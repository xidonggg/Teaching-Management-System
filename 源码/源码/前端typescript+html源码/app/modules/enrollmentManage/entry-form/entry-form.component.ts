import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormsModule, Validators} from '@angular/forms';
import {mobileValidator} from '../../../tools/validators/validators';
import {Clue, EnrollmentPlan, EnrollmentServiceService} from '../enrollment-service.service';
import {LocationStrategy, PathLocationStrategy} from '@angular/common';

@Component({
  selector: 'app-entry-form',
  templateUrl: './entry-form.component.html',
  styleUrls: ['./entry-form.component.css']
})
export class EntryFormComponent implements OnInit {

  private selectedsource:String;
  private selectedPlan:String;
  private selectIndex:Number;
  formModel:FormsModule;
  apiAnswer:any;
  private plan:EnrollmentPlan =new EnrollmentPlan('',null,'',[],'','',[],
    '','','','','','','','','',
    0,0,[]);
  private plans:EnrollmentPlan[] = [new EnrollmentPlan('','','',[],'','',[],
    '','','','','','','','','',
    0,0,[])];
  private clue:Clue;
  style = {
    display: 'block',
    height: '30px',
    lineHeight: '30px'
  };
  constructor(private enrollmentService:EnrollmentServiceService, private location:LocationStrategy) { }

  ngOnInit() {
    //获得招生计划
    this.enrollmentService.getAllPlanings()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的计划");
        console.log(this.apiAnswer);
        this.plans = this.apiAnswer.list;
      })
    //
    // var storage  =  window.sessionStorage;
    // var index = storage.getItem("isform");
    // console.log("在form中，isform的初始值为：");
    // console.log(index);
    // storage.setItem("isform", "true");
    // var index2 = storage.getItem("isform");
    // console.log("在form中，isform的修改后的值为：");
    // console.log(index2);


    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: ['', [Validators.required, Validators.minLength(1)]],
        phone: ['',mobileValidator],
        email: ['']
      }
    )
  }
  sourceChange(){
    console.log(this.selectedsource);
  }
  cancel(){}
  save(){
    // @ts-ignore
    this.clue = this.formModel.value;
    // @ts-ignore
    this.clue.source = this.selectedsource;
    // @ts-ignore
    this.clue.toplan = this.selectedPlan;
    console.log(this.clue);
    this.enrollmentService.addClue(this.clue);
  }
  formchange(){
    console.log(this.selectIndex);
    let index = this.selectIndex;
    // @ts-ignore
    this.plan = this.plans[index];
    // @ts-ignore
    this.selectedPlan = this.plan.id;
  }
}
