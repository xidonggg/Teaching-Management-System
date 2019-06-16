import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {mobileValidator} from '../../../../tools/validators/validators';
import {Staff, StaffManageService} from '../../staff-manage.service';
import {format} from 'date-fns';
import {Role, RoleServiceService} from '../../../organizationManage/organization-manage/role/role-service.service';
import {OrganizationServiceService, OrganizationTree} from '../../../organizationManage/organization-service.service';
import Swal from "sweetalert2";
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {NzMessageService, UploadFile} from 'ng-zorro-antd';
import {Observable, Observer} from 'rxjs';

@Component({
  selector: 'app-staff-form',
  templateUrl: './staff-form.component.html',
  styleUrls: ['./staff-form.component.css']
})
export class StaffFormComponent implements OnInit {

  private staff: Staff = new Staff("","","","","","","","",[],"","","",'','')
  private sexseletedValue = '未知';
  private sex: string[] = ["未知","男","女"];
  formModel: FormGroup;
  date = null; // new Date();入职日期
  private hiredate: string;
  private roles:Role[] = [];
  private organizations: Array<OrganizationTree>;
  private nodes = [];
  private token:string = ""

  listOfOption: Array<{ label: string; value: Role }> = [];
  listOfTagOptions = [];

  constructor(private roleService:RoleServiceService,private organizationService:OrganizationServiceService,
              public http: HttpClient,private staffService:StaffManageService, public router:Router,private msg: NzMessageService) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
  }
  ngOnInit() {
    //表单初始化
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: ['', [Validators.required, Validators.minLength(1)]],
        pinyin:['', [Validators.required, Validators.minLength(1)]],
        phone: ['',mobileValidator],
        password:[''],
        email: [''],
        address: [''],
        idCard: ['']
      }
    );
  this.rolesInit();
  this.departemntInit();
  }
  rolesInit(){
    const children: Array<{ label: string; value: Role }> = [];
    this.roleService.getAllRoles()
      .subscribe((resp)=>{
        console.log("角色初始化");
        console.log(resp);
        this.roles = resp.body.extend.list.roleList;
        console.log(this.roles);
        for (let i = 0; i < this.roles.length; i++) {
          children.push({ label: this.roles[i].name, value: this.roles[i]});
        }
      })
    //角色初始化
    this.listOfOption = children;
    console.log(this.listOfOption);
  }
  departemntInit(){
    this.organizationService.getAllOrganizationTree()
      .subscribe(resp => {
        this.organizations = resp.body.data;
        this.nodes = [
          this.organizations
        ];
        console.log(this.nodes);
      })
  }
  sexchange(value:string){
    console.log(value);
    this.sexseletedValue = value;
  }
  onChangeDate(result: Date): void {
    console.log('onChange: ', result);
    this.hiredate =  format(result, 'YYYY-MM-DD');
    console.log('选择的入职日期');
    console.log(this.hiredate);
  }
  cancel(){
    this.router.navigateByUrl("/staffManage");
  }
  save(){
    this.staff = this.formModel.value;
    this.staff.sex = this.sexseletedValue;
    this.staff.roles = this.listOfTagOptions;
    this.staff.hiredate = this.hiredate;
    this.staff.departments = this.value.toString();
    this.staff.picUrl = this.picUrl;
    console.log("add员工参数");
    console.log(this.staff);

    this.staffService.addStaff(this.staff)
      .subscribe(resp => {
        console.log(resp);
          // @ts-ignore
          if (resp.result.ret == '0') {
            Swal.fire({
              type: 'success',
              title: '成功',
            });
            this.router.navigateByUrl("/staffManage");
          } else {
            Swal.fire({
              type: 'error',
              title: '失败',
            });
          }
        });
  }

  //----------选择所属部门---------------
  value: string[] = [];
  // nodes = [
  //   {
  //     title: 'parent 1',
  //     key: '100',
  //     children: [
  //       {
  //         title: 'parent 1-0',
  //         key: '1001',
  //         children: [
  //           { title: 'leaf 1-0-0', key: '10010', isLeaf: true },
  //           { title: 'leaf 1-0-1', key: '10011', isLeaf: true }
  //         ]
  //       },
  //       {
  //         title: 'parent 1-1',
  //         key: '1002',
  //         children: [{ title: 'leaf 1-1-0', key: '10020', isLeaf: true }]
  //       }
  //     ]
  //   }
  // ];
  onChangeDepartment($event: string[]): void {
    // console.log($event);
    console.log("value:");
    console.log(this.value);
  }
  //---------------上传文件----------------
  uploading = false;
  fileList: UploadFile[] = [];
  apiAnswer: any;

  beforeUpload = (file: UploadFile): boolean => {
    new Observable((observer: Observer<boolean>) => {
      const isJPG = file.type === 'image/jpeg';
      if (!isJPG) {
        this.msg.error('You can only upload JPG file!');
        observer.complete();
        return;
      }
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.msg.error('Image must smaller than 2MB!');
        observer.complete();
        return;
      }
    })
    this.fileList = this.fileList.concat(file);
    return false;
  };

  picUrl:string = "";
  handleUpload(): void {
    const formData = new FormData();
    // tslint:disable-next-line:no-any
    this.fileList.forEach((file: any) => {
      formData.append('file', file);
    });
    formData.append('relativePath', '/file/image');
    this.uploading = true;
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Inherit auth from parent',
        'token': this.token
      }),
      mimeType: 'multipart/form-data'
    };
    this.http.post<HttpResponse<any>>('/springMVC-spring-hibernate/updownFile/userAddFile', formData, httpOptions)
      .pipe()
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log('说好的100会返回');
        this.uploading = false;
        if (this.apiAnswer.result.ret == '0') {
          Swal.fire({
            type: 'success',
            title: '添加成功',
          });
          console.log(resp);
          // @ts-ignore
          this.picUrl = resp.data.fileInfo[0].filePath;
          console.log("图片地址",this.staff.picUrl)
        } else {
          Swal.fire({
            type: 'error',
            title: '添加失败',
          });
        }
      });
  }

  }
