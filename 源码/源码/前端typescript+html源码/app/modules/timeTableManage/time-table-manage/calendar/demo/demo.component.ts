import {Component, ChangeDetectionStrategy, Input} from '@angular/core';
import { Subject } from 'rxjs';
import { OnInit } from '@angular/core';
import {
  CalendarEvent,
  CalendarEventTimesChangedEvent
} from 'angular-calendar';
import { colors } from '../demo-utils/colors';
import { addDays } from 'date-fns';
import {ClassServiceService} from '../../../../classManage/class-service.service';
import Swal from "sweetalert2";
import {TimeTableEvent, TimeTableServiceService} from '../../../time-table-service.service';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit{
  private nodes = [];
  constructor(private classService:ClassServiceService,private timeTableService:TimeTableServiceService) { }

  ngOnInit() {
    //获得班级树
    this.classService.getAllClassTree()
      .subscribe(resp => {
        this.nodes = resp.body.list;
      })
    this.getTimaTable();
  }
  //----------------班级选择---------------
  value: string[] = [];

  onChange($event: string[]): void {
    console.log($event);
    console.log(this.value);
    if(this.value != null && this.value.toString().match('^[a-z0-9]{32}$') == null){
      Swal.fire({
        type: 'error',
        title: '请选择班级',
      })
      this.value = [];
    }else{
      this.getTimaTable();
    }
  }


  timeTableEvent:TimeTableEvent[] = [];
  getTimaTable(){
    this.timeTableService.getAllTimeTable(this.value.toString())
      .subscribe((resp)=>{
        console.log(resp);
        // @ts-ignore
        this.timeTableEvent = resp.list;
        console.log("aaaaaaaaaaaaaa");
        console.log(this.timeTableEvent);
        this.events = [];
        for(let item of this.timeTableEvent) {
          // @ts-ignore
          var t1 = item.start;var t2 = item.end;
          var d1 = t1.replace(/\-/g, "/");var d2 = t2.replace(/\-/g, "/");
          var date1 = new Date(d1);var date2 = new Date(d2);
          // @ts-ignore
          var co = "colors."+item.color;
          let ce: CalendarEvent = {
            // @ts-ignore
            title: item.title,
            color: colors.blue,
            start: date1,
            end: date2,
          };
          this.events.push(ce);
        }
      })
  }

  //------------------时间表--------------
  view: string = 'week';

  viewDate: Date = new Date();

  events: CalendarEvent[] = [];

  refresh: Subject<any> = new Subject();

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd
                    }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.refresh.next();
  }
}
