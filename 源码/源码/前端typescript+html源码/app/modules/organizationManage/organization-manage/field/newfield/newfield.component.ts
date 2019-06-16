import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Field, FieldServiceService, Org} from '../field-service.service';
import Swal from "sweetalert2";
import {Router} from '@angular/router';

@Component({
  selector: 'app-newfield',
  templateUrl: './newfield.component.html',
  styleUrls: ['./newfield.component.css']
})
export class NewfieldComponent implements OnInit {

  formModel: FormGroup;
  field: Field;
  showSad = false;
  harders:any;
  apiAnswer:any;
  orgs: Org[];
  private selectSchool: string;
  constructor(private fieldService: FieldServiceService,private router:Router) { }

  ngOnInit() {
    this.fieldService.getAllSchools()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.orgs = this.apiAnswer.extend.list.getschoolList;
        console.log("this.orgs");
        console.log(this.orgs);
      })

    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
        school: [''],
        location: [''],
        population: ['']
      }
    )
  }

  cancel(){
    window.location.reload();
  }
  save(){
    this.field = {
      "name":this.formModel.value.name,
      "school":this.selectSchool,
      "location":this.formModel.value.location,
      "population": this.formModel.value.population}
    console.log("表单结果");
    console.log(this.field);
    this.fieldService.addField(this.field)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        if(this.apiAnswer.code == '100'){
          Swal.fire({
            type: 'success',
            title: '添加成功',
            text: '添加了一条学生数据到数据库中',
            footer: '<a href>you are the best!</a>'
          })
          this.router.navigateByUrl("/organizationalManage/field")
        }else if(this.apiAnswer.code == '200'){
          Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href>Why do I have this issue?</a>'
          })}
      } );
  }
  schoolId:string = '';
  getChange(id: string) {
    console.log('=========');
    console.log(id);
    this.selectSchool = id;
  }
}
