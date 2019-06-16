import { Component, Input, Output, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-demo-utils',
  templateUrl: './demo-utils.component.html',
  styleUrls: ['./demo-utils.component.css']
})
export class DemoUtilsComponent{

  constructor() { }
  @Input() view: string;

  @Input() viewDate: Date;

  @Input() locale = 'en';

  @Output() viewChange: EventEmitter<string> = new EventEmitter();

  @Output() viewDateChange: EventEmitter<Date> = new EventEmitter();

}
