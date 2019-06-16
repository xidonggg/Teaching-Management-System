import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class FinanceServiceService {

  private token: string = '';
  private getAllTaskUrl = "/springMVC-spring-hibernate/finance/getFinance";
  private saveTsakUrl = "/springMVC-spring-hibernate/finance/saveFinance";
  private getTaskById = "/springMVC-spring-hibernate/finance/getFinanceById";
  private changeTaskState = "/springMVC-spring-hibernate/finance/changeFinanceState";
  private changeOrderStateUrl = "/springMVC-spring-hibernate/finance/changeOrderState";
  private letTaskStopUrl = "/springMVC-spring-hibernate/finance/financeStop";
  private getAllOrderUrl = "/springMVC-spring-hibernate/finance/getFinanceOrders";
  private getAllOrdersByPersonUrl = "/springMVC-spring-hibernate/finance/getFinanceOrdersByPerson";
  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }
  getAllTasks(): Observable<HttpResponse<any>> {
    console.log(this.getAllTaskUrl);
    return this.http.get<any>(
      this.getAllTaskUrl, {observe: 'response'}
    );
  };
  getTask (id: string):Observable<HttpResponse<any>>{
    //   let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }); //其实不表明 json 也可以, ng 默认好像是 json
    console.log("发送的id：");
    console.log(id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    return this.http.post<HttpResponse<any>>(this.getTaskById, "id="+ id, httpOptions)
  };
  changeOrderState(order:FinanceOrder){
    let body = JSON.stringify(order);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    };
    this.http.post<HttpResponse<any>>(this.changeOrderStateUrl, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        // @ts-ignore
        if (resp.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '状态修改成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
  }
  changeFinanceState(task:FinanceTask) {
    let body = JSON.stringify(task);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    };
    this.http.post<HttpResponse<any>>(this.changeTaskState, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        // @ts-ignore
        if (resp.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '状态修改成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
  }
  saveFinance(task:FinanceTask,stuId:string):Observable<HttpResponse<any>>{
    let body = JSON.stringify(task);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8',
        'stuIds':stuId,
        'token':this.token
      })
    };
    return this.http.post<HttpResponse<any>>(this.saveTsakUrl, body, httpOptions)
  }
  letTaskStop(id:string){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset':'utf-8'
      })
    }
    console.log("发送的id为"+id);
    this.http.post<HttpResponse<any>>(this.letTaskStopUrl, "id="+ id, httpOptions);
  }
  getAllOrders(){
    return this.http.get<any>(
      this.getAllOrderUrl, {observe: 'response'}
    );
  }
  getAllOrdersByPerson(){
    return this.http.get<any>(
      this.getAllOrdersByPersonUrl,
      {
        headers: new HttpHeaders({
          'Authorization': 'Inherit auth from parent',
          'charset':'utf-8',
          'token':this.token
        }),
        observe: 'response'}
    );
  }
}

export class FinanceTask{
  [x: string]: any;
  constructor(
    id:string,
    name:string,
    state:string,
    paidNumber:number,
    unpaidNumber:number,
    createPerson:string,
    createTime:string,
    chargeName:string,
    amount:number,
    remarks:string,
    stateChangeTime:string,
    stopTime:string,
    financeOrders:Array<FinanceOrder>
  ){}
}
export class FinanceOrder{
  constructor(
    id:string,
    createTime:string,
    state:string,
    chargeAcount:string,
    stateChangeTime:string,
    taskId:string,
    accountName:string,
    accountPhone:string,
    taskName:string,
    accountPinyin:string,
    chargeName:string,
    amount:number
  ){}
}
