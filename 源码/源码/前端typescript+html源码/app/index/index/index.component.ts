import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  islogin = false;
  constructor() { }
  loginResultHandler(is:boolean){
    this.islogin = is;
}
  ngOnInit() {
    var storage  =  window.localStorage;
    var index = storage.getItem("token");
    if(index != null){
      this.islogin = true;
    }
    console.log("是否登陆"+this.islogin);
  }

}
