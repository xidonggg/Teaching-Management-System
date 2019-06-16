import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from '../modules/studentManage/student-service.service';

@Injectable({
  providedIn: 'root'
})
export class HttpConnectServiceService {

  // configUrl = 'assets/config.json';
  configUrl = '/springMVC-spring-hibernate/person/list';
  addUrl = '/springMVC-spring-hibernate/person/save';
  dataSource: Observable<any>;
  apiAnswer: any;
  harders: any;

  constructor(public http: HttpClient) {

  }

  getConfig() {
    return this.http.get<Config>(this.configUrl);
  }
  getConfigResponse(): Observable<HttpResponse<Config>> {
    return this.http.get<Config>(
      this.configUrl, {observe: 'response'}
    )
  };
  getConfigResponsetry(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.configUrl, {observe: 'response'}
    )
  };

}
export class Config {
  constructor(
    heroesUrl: string,
    textfile: string
  ) {}
}
