import { Component, OnInit } from '@angular/core';
import {SchoolYear, SchoolyearServiceService} from './schoolyear-service.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-school-year',
  templateUrl: './school-year.component.html',
  styleUrls: ['./school-year.component.css']
})
export class SchoolYearComponent implements OnInit {

  apiAnswer: any;
  harders: any;
  schoolyears: SchoolYear;
  private isCreate = false;
  constructor(private schoolYearService: SchoolyearServiceService) { }

  ngOnInit() {
    this.schoolYearService.getAllYears()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.schoolyears = this.apiAnswer.extend.list.schoolYearList;
      })
  }
  create(){
    this.isCreate = true;
  }
  delete(id:string){
    this.schoolYearService.deleteYear2(id)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        if(this.apiAnswer.code == '100'){
          Swal.fire({
            type: 'success',
            title: '删除成功',
            text: '删除了',
            footer: '<a href>you are the best!</a>'
          })
          this.ngOnInit();
        }else{
          Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href>Why do I have this issue?</a>'
          })
        }
      } );;
  }
}
