import { Component, OnInit } from '@angular/core';
import {Authority, Role, RoleServiceService} from './role-service.service';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from "sweetalert2";
@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  authorities: Authority[];
  Allauthorities: Authority[];
  roles: Role[];
  role: Role;
  private apiAnswer: any;// http通信中使用
  private apiAnswer2: any;// http通信中使用
  isCreate = false;

  constructor(private roleService: RoleServiceService, private router: Router) {
    this.isCreate = false;
  }

  ngOnInit() {
    this.isCreate = false;
    this.roleService.getAllAuthorities()
      .subscribe(resp => {
      this.apiAnswer = resp.body;
      console.log(this.apiAnswer);
      this.Allauthorities = this.apiAnswer.extend.list.authorityList;
      this.authorities = this.Allauthorities;
      console.log(this.authorities);

    })
    this.roleService.getAllRoles()
      .subscribe(resp=>{
        this.apiAnswer2 = resp.body;
        console.log(this.apiAnswer2);
        this.roles = this.apiAnswer2.extend.list.roleList;
        console.log(this.roles);
        this.role = this.roles[0];
      })

  }

  roleAuthority(role: Role){
    this.role = role;
    this.authorities = role.authorities;
    console.log("此时的role为");
    console.log(role);
  }

  create(){
 //   this.router.navigateByUrl('/role/newrole');
    this.isCreate = true;

  }
  cancel(){
    this.isCreate = false;
  }

  delete(id: string){
    this.roleService.deleteStudent(id)
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
      } );
  }

}
