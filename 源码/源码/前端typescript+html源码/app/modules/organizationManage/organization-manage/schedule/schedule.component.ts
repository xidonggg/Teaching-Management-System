import { Component, OnInit } from '@angular/core';
import {Schedule, ScheduleServiceService} from './schedule-service.service';
import {format} from 'date-fns';
import Swal from "sweetalert2";

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  private schedule:Schedule = new Schedule('','','','');
  private schedules:Schedule[] = [];
  constructor(private scheduleService:ScheduleServiceService) { }

  ngOnInit() {
    this.scheduleService.getAllSchedule()
      .subscribe(resp => {
        console.log("初始化结果")
        console.log(resp);
        // @ts-ignore
        this.schedules = resp.body.list;
      })
  }
  startTime: Date | null = null;
  endTime: Date | null = null;
  value:string = "";
  startTimelog(time: Date): void {
    console.log(time && time.toTimeString());
  }
  endTimelog(time: Date): void {
    console.log(time && time.toTimeString());
  }

  delete(id:string){
    this.scheduleService.deleteSchedule(id)
      .subscribe(resp => {
        // @ts-ignore
        if (resp.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '删除成功',
          });
          this.ngOnInit();
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      })
  }
  save(){
    this.schedule.startTime = format(this.startTime, 'HH:mm:ss');
    this.schedule.endTime = format(this.endTime, 'HH:mm:ss');
    this.schedule.remark = this.value;
    // new Promise ((fulfill,reject) => {
    //   try {
    //     fulfill("success");
    //   } catch (error) {
    //     reject("fail");
    //   }
    // });
    this.scheduleService.saveSchedule(this.schedule)
    .pipe()
      .subscribe(resp => {
        // @ts-ignore
        if (resp.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '新增成功',
          });
          this.ngOnInit();
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
  }
}
