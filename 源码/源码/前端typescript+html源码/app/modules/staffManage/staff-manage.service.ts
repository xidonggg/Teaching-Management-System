import { Injectable } from '@angular/core';
import {Role} from '../organizationManage/organization-manage/role/role-service.service';
import Swal from "sweetalert2";
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StaffManageService {

  private getStaffNameUrl = "/springMVC-spring-hibernate/staff/listname";
  private saveStaffUrl = "/springMVC-spring-hibernate/staff/save";
  private getStaffByDepartment = "/springMVC-spring-hibernate/staff/getStaffByDepartmentId";
  private staffStateChangeUrl = "/springMVC-spring-hibernate/staff/stateChange";
  private getStaffByIdUrl = "/springMVC-spring-hibernate/staff/getStaffById";
  private getPageStaffByDepartment = "/springMVC-spring-hibernate/staff/getPageStaffByDepartmentId";
  private token:string = "";

  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }

  private staffs: Staff[] = []
  getAllStaff(): Staff[] {
    return this.staffs;
  }
  /**
   * 获取员工姓名，按拼音排序
   */
  getStaffNames(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getStaffNameUrl, {observe: 'response'}
    )
  };

  staffStateChange(staff:Staff): Observable<HttpResponse<any>>{
    let body = JSON.stringify(staff);
    console.log(body);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8',
        'token':this.token
      })
    };
    return this.http.post<HttpResponse<any>>(this.staffStateChangeUrl, body, httpOptions)
  }
  addStaff(staff:Staff): Observable<HttpResponse<any>> {
    let body = JSON.stringify(staff);
    console.log(body);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8',
        'token':this.token
      })
    };
    return this.http.post<HttpResponse<any>>(this.saveStaffUrl, body, httpOptions)
  }
  getStaff(id:string): Observable<HttpResponse<any>> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Inherit auth from parent',
      'charset':'utf-8',
      'token':this.token
    })
  }
  return this.http.post<HttpResponse<any>>(this.getStaffByIdUrl, "id="+ id, httpOptions);
  }

  searchByDepartment(id: number):Observable<HttpResponse<any>>{
    console.log("search参数：");
    console.log(id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    return this.http.post<HttpResponse<any>>(this.getStaffByDepartment, "departmentId="+ id, httpOptions);
  }
  searchPageByDepartment(id: number,page:number):Observable<HttpResponse<any>>{
    console.log("ddddddddddd第几页：");
    console.log(page);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    return this.http.post<HttpResponse<any>>(this.getStaffByDepartment, "departmentId="+ id+"&page="+page, httpOptions);
  }
}
export class Page{
  constructor(
    public size:number,
    public start:number,
    public total:number,
    public current:number
  ){}

}
export class Staff {
  constructor(
    public id: string,
    public name: string,
    public sex: string,
    public phone: string,
    public departments: string,
    public hiredate: string,
    public email: string,
    public idCard: string,
    public roles: Role[],
    public password: string,
    public address: string,
    public pinyin:string,
    public state:string,
    public picUrl:string
  ) { }
}
