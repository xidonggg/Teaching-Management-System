import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from "sweetalert2";
import {Clue, EnrollmentPlan, EnrollmentServiceService} from '../enrollment-service.service';

@Component({
  selector: 'app-enrollment-detail',
  templateUrl: './enrollment-detail.component.html',
  styleUrls: ['./enrollment-detail.component.css']
})
export class EnrollmentDetailComponent implements OnInit {

  apiAnswer:any;
  private plan:EnrollmentPlan = new EnrollmentPlan('','','',[],'','',[],'','','','',
    '','','','','',0,0,[])
  private clues:Clue[] = [];
  constructor(private enrollmentService:EnrollmentServiceService, private router: Router, private routeInfo: ActivatedRoute) { }

  ngOnInit() {
    let planId : string = this.routeInfo.snapshot.params.id;
    this.enrollmentService.getPlanById(planId)
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        )
        console.log(this.apiAnswer);
        if(this.apiAnswer.result.ret == '0'){
          console.log("你要的答案");
          this.plan = this.apiAnswer.data;
          // @ts-ignore
          this.clues = this.plan.clues;
          console.log(this.plan);
        }else{
          Swal.fire({
            type: 'error',
            title: 'Oops...',
          })
        }
      });
  }
  cluedetail(clue:Clue){
    // @ts-ignore
    this.router.navigateByUrl("/clueDetail/"+clue.id);
  }
  back(){
    this.router.navigateByUrl("/recruitStudentsManage");
  }

}
