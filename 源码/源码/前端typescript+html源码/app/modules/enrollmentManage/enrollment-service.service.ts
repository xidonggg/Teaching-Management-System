import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from 'sweetalert2';
import {Course} from '../courseManage/course-service.service';
import {Class} from '../classManage/class-service.service';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentServiceService {

  apiAnswer: any;
  private addPlanUrl = '/springMVC-spring-hibernate/enrollment/saveEnrollment';
  private getAllPlansUrl = '/springMVC-spring-hibernate/enrollment/listEnrollmentPlans';
  private getAllCluesUrl = '/springMVC-spring-hibernate/enrollment/listClues';
  private getPlanByIdUrl = '/springMVC-spring-hibernate/enrollment/get/';
  private getClueByIdUrl = '/springMVC-spring-hibernate/enrollment/getClueById/';
  private changeStateUrl = '/springMVC-spring-hibernate/enrollment/updateEnrollment?_method=PUT';
  private changeClueStateUrl = '/springMVC-spring-hibernate/enrollment/updateClue?_method=PUT';
  private updateClueUrl = '/springMVC-spring-hibernate/enrollment/updateClueAll?_method=PUT';
  private getPlaninggUrl = '/springMVC-spring-hibernate/enrollment/listEnrollmentingPlans';
  private saveClueUrl = "/springMVC-spring-hibernate/enrollment/saveClue";
  private token: string = '';

  constructor(public http: HttpClient) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }

  getAllClues(): Observable<HttpResponse<any>> {
    console.log(this.getAllCluesUrl);
    return this.http.get<any>(
      this.getAllCluesUrl, {observe: 'response'}
    );
  };

  getAllPlans(): Observable<HttpResponse<any>> {
    console.log(this.getAllPlansUrl);
    return this.http.get<any>(
      this.getAllPlansUrl, {observe: 'response'}
    );
  };

  getAllPlanings(): Observable<HttpResponse<any>> {
    console.log(this.getAllPlansUrl);
    return this.http.get<any>(
      this.getPlaninggUrl, {observe: 'response'}
    );
  };

  addPlan(plan: EnrollmentPlan): Observable<HttpResponse<any>>  {
    let body = JSON.stringify(plan);
    let headers = new Headers({'Content-Type': 'application/json'}); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'token': this.token
      })
    };
    console.log(body);
    return this.http.post<HttpResponse<any>>(this.addPlanUrl, body, httpOptions)
  }

  addClue(clue: Clue): any {
    let body = JSON.stringify(clue);
    let headers = new Headers({'Content-Type': 'application/json'}); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'token': this.token
      })
    };
     console.log(body);
    this.http.post<HttpResponse<any>>(this.saveClueUrl, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log('说好的100会返回');
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '添加成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '添加失败',
          });
        }
      });
    return this.apiAnswer;
  }

  getPlanById(id: String): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getPlanByIdUrl + id, {observe: 'response'}
    );
  };

  getClueById(id: String): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.getClueByIdUrl + id, {observe: 'response'}
    );
  };

  changePlanState(id: String, state: String) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    };
    console.log('发送的数据');
    console.log(id);
    console.log(state);
    this.http.post<HttpResponse<any>>(this.changeStateUrl, 'id=' + id + '&state=' + state, httpOptions)
      .pipe()
      .subscribe(resp => {
        console.log(this.apiAnswer);
        this.apiAnswer = resp;
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
  }

  changeClueState(id: String, state: String) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Inherit auth from parent',
        'charset': 'utf-8'
      })
    };
    console.log('发送的数据');
    console.log(id);
    console.log(state);
    this.http.post<HttpResponse<any>>(this.changeClueStateUrl, 'id=' + id + '&state=' + state, httpOptions)
      .pipe()
      .subscribe(resp => {
        console.log(this.apiAnswer);
        this.apiAnswer = resp;
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
  }

  updateClue(clue: Clue): any {
    let body = JSON.stringify(clue);
    let headers = new Headers({'Content-Type': 'application/json'}); //其实不表明 json 也可以, ng 默认好像是 json
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Inherit auth from parent',
        'token': this.token
      })
    };
    console.log(body);
    this.http.post<HttpResponse<any>>(this.updateClueUrl, body, httpOptions)
      .pipe()
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log('说好的100会返回');
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '成功',
          });
        } else {
          Swal.fire({
            type: 'error',
            title: '失败',
          });
        }
      });
    return this.apiAnswer;
  }
}

export class EnrollmentPlan {
  constructor(
    id: String,
    name: String,
    myclasses: String,
    myclassesclass: Class[],
    myclassesname: String,
    courses: String,
    coursesclass: Course[],
    coursesname: String,
    startTime: String,
    endTime: String,
    establishPerson: String,
    eatablishTime: String,
    describe: String,
    communicateStaffname: String,
    communicateStaffPhone: String,
    state: String,
    planNumber: number,
    enrolmentNumber: number,
    clues: Clue[]
  ) {
  }
}

export class Clue {
  constructor(
    id: String,
    name: String,
    phone: String,
    email: String,
    choisecourse: String,
    registrationTime: String,
    intentionaliy: number,
    source: String,
    communicationRecords: Array<CommunicationRecord>,
    toplan: String,
    state: String,
    planName: String
  ) {
  }
}

export class CommunicationRecord {
  constructor(
    id: String,
    style: String,
    content: String,
    contentPersonName: String,
    communicateTime: String
  ) {
  }
}
