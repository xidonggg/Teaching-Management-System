import { Component, OnInit } from '@angular/core';
import {FinanceOrder, FinanceServiceService, FinanceTask} from '../finance-service.service';
import {EnrollmentPlan} from '../../enrollmentManage/enrollment-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ClassServiceService} from '../../classManage/class-service.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Student, StudentServiceService} from '../../studentManage/student-service.service';
import Swal from "sweetalert2";
import {Router} from '@angular/router';
import {format} from "date-fns";

@Component({
  selector: 'app-finance-manage',
  templateUrl: './finance-manage.component.html',
  styleUrls: ['./finance-manage.component.css']
})
export class FinanceManageComponent implements OnInit {

  harders: any;
  private nodes = [];
  formModel: FormGroup;
  selectedState:String[] = [];
  apiAnswer:any;
  showremarks:string[] = [];
  private taskss: FinanceTask[] = [new FinanceTask('','','',0,0,'','','',0,'','','',[
    new FinanceOrder('','','','','','','','','','','',0)
  ])];
  private task:FinanceTask = new FinanceTask('','','',0,0,'','','',0,'','','',[
    new FinanceOrder('','','','','','','','','','','',0)]);
  private orders: FinanceOrder[] = [];
  private myorders: FinanceOrder[] = [];
//  private task: FinanceTask = new FinanceTask('','','',0,0,'','','',0,'','','',[])
  constructor(private financeService:FinanceServiceService,private classService: ClassServiceService,
              public msg: NzMessageService,private stuService:StudentServiceService,
              private router:Router) { }
  stulist: Array<{ key: string; title: string; description: string; direction: string }> = [];
  ngOnInit() {
    this.studatachange();
    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: [''],
        chargeName:[''],
        amount:[''],
        remarks: ['']
      }
    );
    //获得班级树
    this.classService.getAllClassTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        this.harders = keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        this.nodes = this.apiAnswer.list;
      })
    //初始化财务任务
    this.financeService.getAllTasks()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的收费任务");
        console.log(this.apiAnswer);
        this.taskss = this.apiAnswer.list;
        console.log(this.taskss);
        for(let i= 0; i < this.taskss.length; i++){
          // @ts-ignore
          this.selectedState[i] = this.taskss[i].state;
          // @ts-ignore
          if(this.taskss[i].remarks.length > 2){
            this.showremarks[i] = this.taskss[i].remarks.toString().substr(0,2)+"..."
          }else{
            this.showremarks[i] = this.taskss[i].remarks;
          }
          let data = new Date();
          var d1 = this.taskss[i].stopTime.replace(/\-/g, "/");
          var date1 = new Date(d1);
          console.log("oooooooooooooooooooooo");
          console.log(data);
          console.log(date1);
          console.log(data > date1);
          if(data > date1 && this.taskss[i].state != "停止"){
            console.log("有发送");
            this.financeService.letTaskStop(this.taskss[i].id);
          }
        }
      })
    //初始化订单
    this.financeService.getAllOrders()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的全部订单");
        console.log(this.apiAnswer);
        this.orders = this.apiAnswer.list;
      })
    //初始化我的订单
    this.financeService.getAllOrdersByPerson()
      .subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log("获得的全部订单");
        console.log(this.apiAnswer);
        this.myorders = this.apiAnswer.list;
      })
  }
  statechange(task:FinanceTask,state:String){
    console.log(this.selectedState);
    console.log("this.task");
    console.log(task);
    // @ts-ignore
    task.state = state;
     this.financeService.changeFinanceState(task);
  }
  //----------------班级选择---------------
  students:Student[] = [];
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
    }
    console.log(this.value);
    this.studatachange();
  }
  studatachange(){
    const ret: Array<{ key: string; title: string; description: string; direction: string }> = [];
    if(this.value != null){
      this.stuService.getStudentByclassId(this.value.toString())
        .subscribe(resp => {
          // @ts-ignore
          this.students = resp.list;
          console.log(this.students);
          this.students.forEach((value1, index) => {
            ret.push({
              key: value1.pinyin,
              title: value1.name,
              description: value1.pinyin,
              direction: 'left'
            })
          })
          this.stulist = ret;
          console.log("初始化stud");
          console.log(ret);
        });
    }
  }
  save(){
    this.task = this.formModel.value;
    this.task.stopTime = this.stopTime;
    console.log(this.task);
    console.log(this.secectedStudents);
    this.financeService.saveFinance(this.task,this.secectedStudents)
      .subscribe(resp => {
        // @ts-ignore
        if (resp.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '状态修改成功',
          });
          this.ngOnInit();
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });;

  }

  //--------------学生选择-----------------


  stuselect(ret: {}): void {
    console.log('nzSelectChange', ret);
  }

  stuchange(ret: {}): void {
//    console.log('nzChange', ret);
    // @ts-ignore
    for(let item of ret.list){
      console.log(item);
      if(item.direction == "left")
      {
        item.direction = "right";
      }
      else
      {
        item.direction = "left";
      }
    }
  }

  //--------------学生选择确定--------------
  stuisselected = false;
  secectedStudents :string = "";
  confirm(){
    this.stulist.forEach(((value1, index) => {
      if(value1.direction == "right"){
        this.secectedStudents += value1.key+",";
      }
    }))
    this.secectedStudents = this.secectedStudents.substr(0,this.secectedStudents.length-1);
    this.stuisselected = true;
  }
  rechoise(){
    this.stuisselected = false;
  }

  //--------------查看详细信息-----------
  taskdetail(id:string){
    this.router.navigateByUrl("/financeDetail/"+id);
  }

  //-----------时间选择-----------------
  stopTime:string;
  ondataChange(result: Date): void {
    console.log('Selected Time: ', result);
    this.stopTime =  format(result, 'YYYY-MM-DD HH:mm:ss');
    console.log(this.stopTime);
  }

  ondataOk(result: Date): void {
    console.log('onOk', result);
    this.stopTime =  format(result, 'YYYY-MM-DD HH:mm:ss');
    console.log(this.stopTime);
  }
  //--------------缴费---------------------
  pay(order:FinanceOrder){
      // @ts-ignore
      order.state = "已缴";
      console.log(order);
      this.financeService.changeOrderState(order);
    }
}
