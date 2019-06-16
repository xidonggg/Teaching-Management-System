import {Component, Input, OnInit} from '@angular/core';
import {Authority, Role, RoleServiceService} from '../role-service.service';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import Swal from "sweetalert2";

@Component({
  selector: 'app-newrole',
  templateUrl: './newrole.component.html',
  styleUrls: ['./newrole.component.css']
})
export class NewroleComponent implements OnInit {

  private Allauthorities: Authority[];
  private authorityFormControls: Array<FormControl> = new Array<FormControl>();
  // private newAuthorityFormControls: Array<FormControl> = new Array<FormControl>();
  formModel: FormGroup;
  private apiAnswer: any;// http通信中使用
  private role: Role;

  checkOptionsOne: Array<{ label: string; value: string; checked: boolean }> = [];
  constructor(private roleService: RoleServiceService, private router: Router) {
  }
  // checkOptionsOne = [
  //   { label: 'Apple', value: 'Apple', checked: true },
  //   { label: 'Pear', value: 'Pear' },
  //   { label: 'Orange', value: 'Orange' }
  // ];
  log(value: object[]): void {
    console.log(value);
  }
  ngOnInit() {



    // for(let i =0; i < 10; i++){
    //   this.authorityFormControls.push(new FormControl(false));
    // }
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
//        categories: fb.array(this.authorityFormControls)
      }
    )
    this.roleService.getAllAuthorities()
      .subscribe(resp => {
        this.apiAnswer = resp.body;
        this.Allauthorities = this.apiAnswer.extend.list.authorityList;
        // for(let i of this.Allauthorities)
        // {
        //   this.newAuthorityFormControls.push(new FormControl(false));
        // }
        // console.log("创建的formModel长度");
        // console.log(this.newAuthorityFormControls.length);
        this.formModel.reset({
          name: '',
//          categories:this.newAuthorityFormControls,
        })
//        console.log(this.newAuthorityFormControls);
        console.log(this.formModel);
        console.log( this.formModel.value);
        const children: Array<{ label: string; value: string; checked: boolean }> = [];
        for(let i = 0; i < this.Allauthorities.length; i++){
          children.push({ label: this.Allauthorities[i].name, value: this.Allauthorities[i].name, checked: false });
        }
        this.checkOptionsOne = children;
      })
  }
  click(){
    // var chineseCategories = [];
    // var index = 0;
    // for(var i = 0; i < 30; i++) {
    //   if(this.formModel.value.categories[i] && i < this.newAuthorityFormControls.length) {
    //     chineseCategories[index++] = this.Allauthorities[i];
    //   }
    // }
   // this.formModel.value.authorities = chineseCategories;
    this.role = this.formModel.value;
    let checkAuth:Array<Authority> = [];
    for(let i=0; i < this.checkOptionsOne.length; i++){
      if(this.checkOptionsOne[i].checked){
        checkAuth.push(this.Allauthorities[i]);
      }
    }
    this.role.authorities = checkAuth;
    console.log(JSON.stringify(this.role));
    console.log(this.role);
    this.roleService.addRole(this.role)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        if(this.apiAnswer.code == '100'){
          Swal.fire({
            type: 'success',
            title: '添加成功',
            text: '添加了一个角色数据到数据库中',
            footer: '<a href>you are the best!</a>'
          })
          this.router.navigateByUrl("/organizationalManage/role");
        }else if(this.apiAnswer.code == '200'){
          Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href>Why do I have this issue?</a>'
          })}
      } );
  }
  cancel(){
    this.router.navigateByUrl("/organizationalManage/role");
  }
}
