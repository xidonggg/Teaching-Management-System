import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Organization, OrganizationServiceService} from '../../../organization-service.service';

@Component({
  selector: 'app-organization-message',
  templateUrl: './organization-message.component.html',
  styleUrls: ['./organization-message.component.css']
})
export class OrganizationMessageComponent implements OnInit {

  id: number;
  apiAnswer: any;
  harders: any;
  organization: Organization = new Organization(0,'','','',true,0,[])
  constructor(private routeInfo: ActivatedRoute, private organizationService: OrganizationServiceService) { }

  ngOnInit() {
    this.id = this.routeInfo.snapshot.params.id;
    this.organizationService.getOrganizationsById(this.id)
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.organization = this.apiAnswer.extend.list.organizationList;
        console.log("根据id:"+this.id+"查organization:");
        console.log(this.organization);

      });
  }

}
