import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FinanceOrder, FinanceServiceService, FinanceTask} from '../finance-service.service';

@Component({
  selector: 'app-finance-deatil',
  templateUrl: './finance-deatil.component.html',
  styleUrls: ['./finance-deatil.component.css']
})
export class FinanceDeatilComponent implements OnInit {
  apiAnswer:any;
  selectedState:String[] = [];
  private task:FinanceTask = new FinanceTask('','','',0,0,'','','',0,'','','',[
    new FinanceOrder('','','','','','','','','','','',0)]);
  constructor(private router: Router, private routeInfo: ActivatedRoute, private financeService:FinanceServiceService) { }

  ngOnInit() {
    let taskId : string = this.routeInfo.snapshot.params.id;
    this.financeService.getTask(taskId)
      .subscribe(resp => {
        // @ts-ignore
        this.task = resp.data;
        console.log("任务信息初始化");
        console.log(this.task);
        for(let i= 0; i < this.task.financeOrders.length; i++) {
          // @ts-ignore
          this.selectedState[i] = this.task.financeOrders[i].state;
        }
      })
  }
  back(){
    this.router.navigateByUrl("/financeManage");
  }
  statechange(order:FinanceOrder,state:String){
    console.log(state);
    // @ts-ignore
    order.state = state;
    console.log(order);
    this.financeService.changeOrderState(order);
  }
}
