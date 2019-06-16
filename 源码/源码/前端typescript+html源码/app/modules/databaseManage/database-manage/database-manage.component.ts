import {Component, OnInit} from '@angular/core';
import {FieldServiceService, Org} from '../../organizationManage/organization-manage/field/field-service.service';
import {OrganizationServiceService, OrganizationTree} from '../../organizationManage/organization-service.service';
import {NzFormatEmitEvent, NzMessageService, UploadFile} from 'ng-zorro-antd';
import {DatabaseServiceService, Folder, FolderTree} from '../database-service.service';
import Swal from 'sweetalert2';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-database-manage',
  templateUrl: './database-manage.component.html',
  styleUrls: ['./database-manage.component.css']
})
export class DatabaseManageComponent implements OnInit {

  uploading = false;
  fileList: UploadFile[] = [];
  apiAnswer: any;


  private seclctedDepartment:string = null;
  private nodes;
  private organizations: Array<OrganizationTree>;
  private folderTree: FolderTree<Folder>;
  private folderTrees: Array<FolderTree<Folder>>;
  private files: File[] = [];

  constructor(private organizationService: OrganizationServiceService, private databaseService: DatabaseServiceService, private http: HttpClient, private msg: NzMessageService) {
  }

  ngOnInit() {
    this.getMyDepartment();
    this.organizationService.getAllOrganizationTree()
      .subscribe(resp => {
        const keys = resp.headers.keys();
        keys.map(
          key => this.apiAnswer = {...resp.body}
        );
        console.log(this.apiAnswer);
        this.organizations = this.apiAnswer.data;
        this.nodes = [
          this.organizations
        ];
        console.log(this.nodes);
      });
    console.log("初始化时的seclctedDepartment");
    console.log(this.seclctedDepartment);
    this.databaseService.getFolderNames(this.seclctedDepartment)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("结果:");
        console.log(this.apiAnswer);
        if(this.apiAnswer.result.ret == '0'){
          this.folderTree = this.apiAnswer.data;
          console.log("看你去了哪里");
          console.log(this.apiAnswer.data);
          if(this.apiAnswer.data == null)
          {
            this.folderTrees = [];
            console.log("1");
          }
          else{
            this.folderTrees = [
              this.folderTree
            ];
            console.log("2");
          }
          console.log('folderTree:');
          console.log(this.folderTrees);
        }else if(this.apiAnswer.result.ret == '1'){
          Swal.fire({
            type: 'error',
            title: 'Oops...',
          })}
        // const keys = resp.headers.keys();
        // keys.map(
        //   key => this.apiAnswer = {...resp.body}
        // );
        // this.folderTree = this.apiAnswer.data;
        // this.folderTrees = [
        //   this.folderTree
        // ];
        // console.log('folderTree:');
        // console.log(this.folderTrees);
      });
  }

  public myDepartment:string[] = [];
  getMyDepartment(){
    this.databaseService.getMyDepartment()
      .subscribe(resp=>{
        console.log("我所在的部门");
        console.log(resp);
        this.myDepartment = resp.body.list;
        console.log(this.myDepartment)
      })
  }
  /**
   * 选择部门
   * @param event
   */
  nzEvent(event: NzFormatEmitEvent): void {
    console.log(event.keys);
    this.seclctedDepartment = event.keys[0];
    //判断部门是否为自己的部门
    let flag = false;
    for(let i = 0; i < this.myDepartment.length; i++){
      if(this.seclctedDepartment == this.myDepartment[i]){
        flag = true;
      }
    }
    if(flag){
      this.ngOnInit();
    }else{
      Swal.fire({
        type: 'error',
        title: '没有该部门权限',
      });
    }

  }

  /**
   * 点击文件夹
   */
  selectedFolder: string[];//选择的文件夹id
  nzFolderEvent(event: NzFormatEmitEvent): void {
    console.log(event.keys);
    this.selectedFolder = event.keys;
    this.getFile();
  }

  download(path:string){
    window.open(path,'_blank');
  }
  getFile() {
    if (this.selectedFolder == undefined || this.selectedFolder.length == 0) {
      Swal.fire({
        type: 'error',
        title: '请选择一个文件夹',
      });
    } else {
      this.databaseService.getFilesByFolder(this.selectedFolder[0])
        .pipe()
        .subscribe(resp => {
          this.apiAnswer = resp;
          console.log('说好的100会返回');
          console.log(this.apiAnswer);
          if (this.apiAnswer.result.ret != '0') {
            Swal.fire({
              type: 'error',
              title: 'Oops...',
            });
          } else {
            this.files = this.apiAnswer.list;
            console.log('找到的文件');
            console.log(this.files);
          }
        });
    }
  }

  selectedAddFolder = true;

  /**
   * 新增删除文件夹
   */
  addFoldder() {
    console.log(this.selectedFolder);
    if (this.selectedFolder == undefined || this.selectedFolder.length == 0) {
      Swal.fire({
        type: 'error',
        title: '请选择一个文件夹',
      });
    } else {
      if (this.selectedAddFolder == true) {
        this.selectedAddFolder = false;
      } else {
        this.selectedAddFolder = true;
      }
    }
  }

  deleteFolder() {
    console.log(this.selectedFolder);
    if (this.selectedFolder == undefined || this.selectedFolder.length == 0) {
      Swal.fire({
        type: 'error',
        title: '请选择一个文件夹',
      });
    } else {
      console.log(this.selectedFolder);
      this.databaseService.deleteFolder(this.selectedFolder[0])
        .subscribe(resp => {
          this.apiAnswer = resp;
          console.log("说好的100会返回")
          console.log(this.apiAnswer);
          if(this.apiAnswer.result.ret == '0'){
            Swal.fire({
              type: 'success',
              title: '删除成功',
            })
            this.update();
          }else if(this.apiAnswer.result.ret == '1'){
            Swal.fire({
              type: 'error',
              title: this.apiAnswer.result.msg,
            })}
        } );;
    }
  }

  foldername: string;//新建文件夹名称
  folder: Folder = new Folder(null, null, null, null, null, null);

  addFolderConfirm() {
    console.log(this.selectedFolder);
    console.log(this.foldername);
    // @ts-ignore
    this.folder.name = this.foldername;
    // @ts-ignore
    this.folder.relativePath = this.selectedFolder[0];
    console.log('参数');
    console.log(this.folder);
    this.selectedAddFolder = true;

    this.databaseService.addFolder(this.folder)
      .subscribe(resp => {
        this.apiAnswer = resp;
        console.log("说好的100会返回")
        console.log(this.apiAnswer);
        if(this.apiAnswer.result.ret == '0'){
          Swal.fire({
            type: 'success',
            title: '添加成功',
          })
          this.update();
        }else if(this.apiAnswer.result.ret == '1'){
          Swal.fire({
            type: 'error',
            title: 'Oops...',
          })}
      } );;
  }

  update() {
    this.ngOnInit();
  }


  //---------------上传文件----------------
  beforeUpload = (file: UploadFile): boolean => {
    this.fileList = this.fileList.concat(file);
    return false;
  };

  handleUpload(): void {
    const formData = new FormData();
    // tslint:disable-next-line:no-any
    this.fileList.forEach((file: any) => {
      formData.append('file', file);
    });
    formData.append('relativePath', this.selectedFolder[0]);
    this.uploading = true;
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Inherit auth from parent',
        'token': 'fdgfdg_f92bc66bfcef466aa2600b58382962a6'
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
          this.getFile();
          this.fileList = [];
        } else {
          Swal.fire({
            type: 'error',
            title: '添加失败',
          });
        }
      });

  }
}
