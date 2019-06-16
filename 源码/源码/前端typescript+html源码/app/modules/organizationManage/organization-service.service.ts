import { Injectable } from '@angular/core';
import {Menu} from '../../composition/menu/menu.component';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrganizationServiceService {
  organizations: Array<Organization>;
  private getAllUrl = '/springMVC-spring-hibernate/organization/list';
  private getByIdUrl = '/springMVC-spring-hibernate/organization/get/';
  private getorganizationTreeUrl = '/springMVC-spring-hibernate/organization/getorganizationTree';
  constructor(public http: HttpClient) {
    // this.organizations =
    //   [new Organization(0, '全部', true, 0, [
    //     new Organization(1, '杭州校区', true, 0, [
    //       new Organization(101, '教务处', false, 1, [
    //         new Organization(10101, '部门1', false, 2, []),
    //         new Organization(10102, '部门2', true, 2, [])]),
    //       new Organization(102, '学工办', true, 1, [])]),
    //     new Organization(2, '北京校区', false, 0, [
    //       new Organization(101, '教务处1', true, 1, []),
    //       new Organization(101, '教务处2', true, 1,  [])
    //     ]),
    //     new Organization(3, '四川校区', true, 0, [])
    //   ])];

  }
  getAllOrganizations(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getAllUrl, {observe: 'response'}
    )
  };
  getOrganizationsById(id: number): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getByIdUrl+id, {observe: 'response'}
    )
  };

  getAllOrganizationTree(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getorganizationTreeUrl, {observe: 'response'}
    )
  };

}

export class Organization {
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public attribute: string,
    public open: boolean,
    public deep: number,
    public organization: Organization[]
  ) { }
}
export class OrganizationTree{
  constructor(
    public title: string,
    public key: string,
    public expanded: boolean,
    public icon: string,
    public children: OrganizationTree,
    public isLeaf:boolean
  ){}
}
