import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class DatabaseServiceService {

  apiAnswer:any;
  private getFolders = "/springMVC-spring-hibernate/updownFile/getFolderTree";
  private addFolderUrl = "/springMVC-spring-hibernate/updownFile/saveFolder2";
  private deleteFolderUrl = "/springMVC-spring-hibernate/updownFile/deleteFolder";
  private getFilesByFolderUrl = "/springMVC-spring-hibernate/updownFile/getFileByFolder";
  private getMyDepartmentUrl ="/springMVC-spring-hibernate/staff/getMyDepartment";
  token:string;
  constructor(public http: HttpClient) {
    var storage  =  window.localStorage;
    this.token = storage.getItem("token");
  }
  getMyDepartment(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getMyDepartmentUrl, {
        observe: 'response',
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'charset': 'utf-8',
          'token': this.token
        })
      }
    )
  }
  getFolderNames(departmentPath: string): Observable<HttpResponse<any>> {
    console.log("结果发送的path:");
    console.log(departmentPath);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    return this.http.post<HttpResponse<any>>(this.getFolders, "departmentPath=/"+ departmentPath, httpOptions)
  };
  deleteFolder (path: string): Observable<HttpResponse<any>> {
    //   let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    console.log("进入删除方法了，id:");
    console.log(path);
    return this.http.post<HttpResponse<any>>(this.deleteFolderUrl, "relativePath="+ path, httpOptions);

  };
  addFolder (folder: Folder): Observable<HttpResponse<any>>{
    let body = JSON.stringify(folder);
    let headers = new Headers({ 'Content-Type': 'application/json' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'token': this.token
      })
    }
    console.log("JSON值");
    console.log(body);
    return this.http.post<HttpResponse<any>>(this.addFolderUrl, body, httpOptions);
  }

  getFilesByFolder (path: string): Observable<HttpResponse<any>>{
    //   let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8',
        'token':this.token
      })
    }
    console.log("进入查找文件方法了，id:");
    console.log(path);
    return this.http.post<HttpResponse<any>>(this.getFilesByFolderUrl, "relativePath="+ path, httpOptions)
  };

}
export class FolderTree<T>{
  constructor(
    public title:String,
    public key:string,
    public expanded:boolean,
    public icon:string,
    public children:FolderTree<T>[],
    public isLeaf:boolean,
    public t:T
  ){}
}

export class Folder{
  constructor(
    id:number,
    name:string,
    parentId:number,
    relativePath:string,
    eatablishTime:string,
    eatablishPerson:string
  ){}
}
export class File{
  constructor(
    public id:string,
    public fileName:string,
    public filePath:string,
    public relationID:string,
    public uploadTime:string,
    public relativePath:string
  ){}
}
