import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  private myauthorities :string[] = ["查看学生信息","查看班级信息"];
  constructor() { }
  getAuthorities(){
    return this.myauthorities;
  }
}

