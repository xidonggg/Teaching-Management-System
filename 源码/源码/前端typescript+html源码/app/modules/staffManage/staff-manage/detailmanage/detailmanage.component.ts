import {Component, Input, OnInit} from '@angular/core';
import {Staff, StaffManageService} from '../../staff-manage.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-detailmanage',
  templateUrl: './detailmanage.component.html',
  styleUrls: ['./detailmanage.component.css']
})
export class DetailmanageComponent implements OnInit {

  private staff: Staff = new Staff('','','','','','','','',[],'','','','','');
  roles:string = "";
  constructor(private staffService: StaffManageService, private routeInfo: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    let staffId : string = this.routeInfo.snapshot.params.id;
    console.log(staffId);
    this.staffService.getStaff(staffId)
      .subscribe(resp=>{
        console.log(resp);
        // @ts-ignore
        this.staff = resp.data;
        for(let i = 0; i < this.staff.roles.length; i++){
          this.roles += this.staff.roles[i].name + ","
        }
        this.roles = this.roles.substr(0,this.roles.length-1);
      });
  }

  back(){
    this.router.navigateByUrl("/staffManage")
  }
}
