import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RoleServiceService {

  private getAllAuthorityUrl = "/springMVC-spring-hibernate/role/getAllAuthorities";
  private getAllRolesUrl = "/springMVC-spring-hibernate/role/getAllRoles";
  private addRoleUrl = "/springMVC-spring-hibernate/role/addRole";
  private deleteRoleUrl = "/springMVC-spring-hibernate/role/deleteRole?_method=DELETE";
  apiAnswer: any;
  harders: any;
  authorities: Array<Authority>;
  constructor(public http: HttpClient) { }

  getAllAuthorities(): Observable<HttpResponse<any>>{
    return this.http.get<any>(
      this.getAllAuthorityUrl, {observe: 'response'}
    )
  };

  getAllRoles(): Observable<HttpResponse<any>>{
    return this.http.get<any>(
      this.getAllRolesUrl, {observe: 'response'}
    )
  };
  /*
* 用post方法请求
* */
  deleteStudent (id: string) : Observable<HttpResponse<any>>{
    //   let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    console.log("进入删除方法了，id:");
    console.log(id);
    return this.http.post<HttpResponse<any>>(this.deleteRoleUrl, "id="+ id, httpOptions);
  };
  /*
  * 用post方法请求
  * */
  addRole (role: Role): Observable<HttpResponse<any>> {
    let body = JSON.stringify(role);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent'
      })
    }
    console.log("role:");
    console.log(role);
    return this.http.post<HttpResponse<any>>(this.addRoleUrl, body, httpOptions)

  }
}

export class Authority{
  constructor(
    public id: string,
    public name: string,
    public underMoudle: string,
  ){}
}
export class Role{
  constructor(
    public id: string,
    public name: string,
    public authorities: Authority[],
  ){}
}
