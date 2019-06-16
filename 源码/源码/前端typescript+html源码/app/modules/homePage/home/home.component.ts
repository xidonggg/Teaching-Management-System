import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';


declare let laydate;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // show = false;
  constructor() {
  }

  ngOnInit() {
    // laydate.render({
    //   elem: '#s',
    //   theme: '#4DC6FD',
    //   done: (value, date, endDate) => {
    //     console.log(value);
    //     console.log(date);
    //     console.log(endDate);
    //   }
    // });
  }

}
