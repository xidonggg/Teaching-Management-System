import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-organization-manage',
  templateUrl: './organization-manage.component.html',
  styleUrls: ['./organization-manage.component.css']
})
export class OrganizationManageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onclickOrganization(){
    this.router.navigateByUrl('/organizationalManage/organizational');
  }
  onclickRole(){
    this.router.navigateByUrl('/organizationalManage/role');
  }
  onclickYear(){
    this.router.navigateByUrl('/organizationalManage/year');
  }
  onclickSchedule(){
    this.router.navigateByUrl('/organizationalManage/schedule');
  }
  onclickField(){
    this.router.navigateByUrl('/organizationalManage/field');
  }


}
