import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {mobileValidator} from '../../../../../tools/validators/validators';
import {SchoolYear, SchoolyearServiceService} from '../schoolyear-service.service';
// @ts-ignore
import endOfMonth from 'date-fns/end_of_month';
// @ts-ignore
import format from 'date-fns/format';
// declare let laydate;
@Component({
  selector: 'app-newschoolyear',
  templateUrl: './newschoolyear.component.html',
  styleUrls: ['./newschoolyear.component.css']
})
export class NewschoolyearComponent implements OnInit {

  formModel: FormGroup;
  schoolyear: SchoolYear;
  startTime:string;
  endTime:string;
  constructor(private schoolyearService: SchoolyearServiceService) { }

  ngOnInit() {
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
      }
    )
    // laydate.render({
    //   elem: '#a',
    //   theme: '#4DC6FD',
    //   range: true,
    //   done: (value, date, endDate) => {
    //     console.log("value");
    //     console.log(value);
    //     console.log("date");
    //     console.log(date);
    //     console.log("enddate");
    //     console.log(endDate);
    //     this.schoolyear = new SchoolYear("123",this.formModel.value.toString()
    //       ,date.year+"-"+date.month+"-"+date.date
    //       ,endDate.year+"-"+endDate.month+"-"+endDate.date);
    //     this.startTime = date.year+"-"+date.month+"-"+date.date;
    //     this.endTime = endDate.year+"-"+endDate.month+"-"+endDate.date;
    //     // console.log("schoolyear");
    //     // console.log(this.startTime);
    //     // console.log(this.endTime);
    //     // console.log(this.formModel.value.name);
    //   }
    // });
  }
  cancel(){
    window.location.reload();
  }
  save(){
    this.schoolyear = {
      "name":this.formModel.value.name,
      "startTime":this.startTime,
      "endTime":this.endTime};
    console.log(this.schoolyear);
    this.schoolyearService.addYear(this.schoolyear);
  }

  //------------------日期选择------------
  ranges1 = { Today: [new Date(), new Date()], 'This Month': [new Date(), endOfMonth(new Date())] };

  onChange(result: Date[]): void {
    console.log('From: ', result[0], ', to: ', result[1]);
    this.startTime =  format(result[0], 'YYYY-MM-DD');
    this.endTime = format(result[1], 'YYYY-MM-DD')
    console.log(this.startTime);
    console.log(this.endTime);
  }

}
