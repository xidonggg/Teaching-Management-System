import { Component, OnInit } from '@angular/core';
import {Organization, OrganizationServiceService} from '../../organization-service.service';

@Component({
  selector: 'app-organization-manage-cp',
  templateUrl: './organization-manage-cp.component.html',
  styleUrls: ['./organization-manage-cp.component.css']
})
export class OrganizationManageCPComponent implements OnInit {

  clickOrganization: Organization;
  organizations: Array<Organization>;
  apiAnswer: any;
  harders: any;
  constructor(private organizationService: OrganizationServiceService) { }

  ngOnInit() {
    this.organizationService.getAllOrganizations()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.organizations = this.apiAnswer.extend.list.organizationList;
      })
  }

  getClickOrganization(organization: Organization) {
    console.log('得到了点击item:');
    this.clickOrganization = organization;
    console.log('得到了点击item:' + organization.name);
  }


}
