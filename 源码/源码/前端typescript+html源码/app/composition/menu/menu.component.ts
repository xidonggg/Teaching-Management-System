import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menus: Array<Menu>;
  showmenus: Array<Menu> = [];
  currentMenuId: number;
  private myauthorities :string[] = [];
  private needAuth :string[] = [];
  constructor(public router: Router) {

  }

  ngOnInit() {
    console.log("menu的ngInit")
    var storage = window.localStorage;
    var index = storage.getItem("authority");
    var pinyin = storage.getItem("pinyin");
    console.log(pinyin);
    console.log("菜单我的权限:");
    if (index != null) {
      this.myauthorities = index.split(",");

      this.menus = [
        new Menu(0, '首页', 'homePage', 'glyphicon glyphicon-home', [], ''),
        new Menu(1, '招生', 'recruitStudentsManage', 'glyphicon glyphicon-home', [], '查看招生计划权限'),
        new Menu(2, '学生', 'studentManage', 'glyphicon glyphicon-user', [], '查看学生权限'),
        new Menu(3, '班级', 'classManage', 'glyphicon glyphicon-home', [], '查看班级权限'),
        new Menu(4, '课表', 'timeTableManage', 'glyphicon glyphicon-home', [], '查看选课权限,学生'),
       new Menu(5, '课程', 'courseManage', 'glyphicon glyphicon-home', [], '查看课程权限'),
        new Menu(6, '成绩', 'achievementManage', 'glyphicon glyphicon-home', [], '查看成绩权限'),
        new Menu(7, '员工', 'staffManage', 'glyphicon glyphicon-home', [], '查看学生权限'),
        new Menu(8, '财务', 'financeManage', 'glyphicon glyphicon-home', [], '查看缴费任务权限,学生'),
        new Menu(9, '资料库', 'databaseManage', 'glyphicon glyphicon-home', [], '查看文件权限'),
        new Menu(10, '机构管理', 'organizationalManage', 'glyphicon glyphicon-home', [], '机构设置权限'),
      ];
   console.log("监视循环")
//    console.log(this.myauthorities);
      let flag:number = 0;
      for (let i = 0; i < this.menus.length; i++) {
        for (let j = 0; j < this.myauthorities.length; j++) {
//        console.log(this.menus[i].auth+"menu");
//        console.log(this.myauthorities[j]+"auth")
          this.needAuth = this.menus[i].auth.split(",");
          for(let k = 0; k < this.needAuth.length; k++){
            if (this.needAuth[k] == this.myauthorities[j]) {
              if(flag != 0 && this.showmenus[flag-1].id != this.menus[i].id)
              {
                console.log(i+"*"+this.menus[i].id+"*"+this.menus[i-1].id)
                this.showmenus.push(this.menus[i]);
                console.log("通过", this.menus[i])
                flag++;
              }
              if(flag == 0){
                console.log(i+"*"+this.menus[i].id+"*"+this.menus[i-1].id)
                this.showmenus.push(this.menus[i]);
                console.log("通过", this.menus[i])
                flag++;
              }
            }
          }
        }
      }
    }
  }
  nav(url: Menu) {
    this.router.navigate([url.link]);

    this.currentMenuId = url.id;
  }

}
export class Menu {
  constructor(public id: number,
              public name: string,
              public link: string,
              public icon: string,
              public child: Menu[],
              public auth:string) {

  }
}
