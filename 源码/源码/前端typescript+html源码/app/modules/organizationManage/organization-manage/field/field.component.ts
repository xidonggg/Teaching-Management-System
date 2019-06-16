import { Component, OnInit } from '@angular/core';
import {Field, FieldServiceService} from './field-service.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.css']
})
export class FieldComponent implements OnInit {
  private apiAnswer: any;
  private harders: any;
  private fields: Field;
  private isCreate = false;

  constructor(private fieldService: FieldServiceService) { }

  ngOnInit() {
    this.fieldService.getAllFields()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.fields = this.apiAnswer.extend.list.schoolYieldList;
      })
  }
  create(){
    this.isCreate = true;
  }
  delete(id:string){
    this.fieldService.deleteField(id)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        if(this.apiAnswer.code == '100'){
          Swal.fire({
            type: 'success',
            title: '删除成功',
            text: '删除了一个角色',
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
