import { Component, OnInit } from '@angular/core';
import {Page, Staff, StaffManageService} from '../staff-manage.service';
import {StudentServiceService} from '../../studentManage/student-service.service';
import {NzFormatEmitEvent} from 'ng-zorro-antd';
import {Router} from '@angular/router';
import {Organization, OrganizationServiceService, OrganizationTree} from '../../organizationManage/organization-service.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-staff-manage',
  templateUrl: './staff-manage.component.html',
  styleUrls: ['./staff-manage.component.css']
})
export class StaffManageComponent implements OnInit {

  selectedState:String[] = [];
  private staffs: Array<Staff> = [];
  harders: any;
  apiAnswer: any;
  private organizations: Array<OrganizationTree>;
  private nameOrId: string;
  private nodes;
  // nodes = [
  //   {
  //     title: 'parent 1',
  //     key: '100',
  //     expanded: true,
  //     icon: 'anticon anticon-smile-o',
  //     children: [
  //       { title: 'leaf', key: '1001', icon: 'anticon anticon-meh-o', isLeaf: true },
  //       { title: 'leaf', key: '1002', icon: 'anticon anticon-frown-o', isLeaf: true }
  //     ]
  //   }
  // ];
  constructor(private staffService: StaffManageService, private router: Router, private organizationService: OrganizationServiceService) {
    this.staffs = staffService.getAllStaff();
    this.organizationService.getAllOrganizationTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log(this.apiAnswer);
        this.organizations = this.apiAnswer.data;
        this.nodes = [
          this.organizations
        ];
        console.log(this.nodes);
      })
  }

  detail(staff: Staff){
    this.router.navigateByUrl("/staffdetail/"+ staff.id)
  }
  ngOnInit() {
  }

  pp:Page;
  page:number = 1;
  departmentId:number =0;
  nzEvent(event: NzFormatEmitEvent): void {
    console.log("查找员工");
    console.log(event.keys);
    if(event.keys.length != 0)
    {
      this.departmentId = parseInt(event.keys[0]);
      this.staffService.searchByDepartment(parseInt(event.keys[0]))
        .subscribe((resp)=>{
          console.log(resp);
          // @ts-ignore
          this.pp = resp.page;
          // @ts-ignore
          this.staffs = resp.list;
          for(let i= 0; i < this.staffs.length; i++){
            // @ts-ignore
            this.selectedState[i] = this.staffs[i].state;
          }
        });
    }
  }
  // goLeft(){
  //   this.page = this.page-1;
  //   if(this.page <= 0){
  //     this.page++;
  //     return ;
  //   }
  //   if(this.departmentId != null){
  //     this.staffService.searchPageByDepartment(this.departmentId ,this.page)
  //       .subscribe((resp)=>{
  //         console.log(resp);
  //         // @ts-ignore
  //         this.pp = resp.page;
  //         // @ts-ignore
  //         this.staffs = resp.list;
  //         for(let i= 0; i < this.staffs.length; i++){
  //           // @ts-ignore
  //           this.selectedState[i] = this.staffs[i].state;
  //         }
  //       });
  //   }
  // }
  // goRight(){
  //   this.page = this.page+1;
  //   if(this.departmentId != null){
  //     this.staffService.searchPageByDepartment(this.departmentId ,this.page)
  //       .subscribe((resp)=>{
  //         console.log(resp);
  //         // @ts-ignore
  //         this.pp = resp.page;
  //         // @ts-ignore
  //         this.staffs = resp.list;
  //         for(let i= 0; i < this.staffs.length; i++){
  //           // @ts-ignore
  //           this.selectedState[i] = this.staffs[i].state;
  //         }
  //       });
  //   }
  // }
  statechange(staff:Staff,state:string){
    // @ts-ignore
    staff.state = state;
    this.staffService.staffStateChange(staff)
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        if(resp.result.ret == "0"){
          Swal.fire({
            type: 'success',
            title: '成功',
          })
        }else{
          Swal.fire({
            type: 'error',
            title: '失败',
          })
        }
      })
  }
  searchByname(){
    console.log("搜索：");
    console.log(this.nameOrId);
  }
  create(){
    this.router.navigateByUrl('/staffform/0');
  }
}
