import {Component, OnInit} from '@angular/core';
import {Student, StudentServiceService} from '../student-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2';
import {mobileValidator} from '../../../tools/validators/validators';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {NzMessageService, UploadFile} from 'ng-zorro-antd';
import {Observable, Observer} from 'rxjs';
/*
* 学生表单界面，id为0时新增学生，否则获取学生信息，修改学生信息
* */
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  student: Student = new Student('0', '' , '', '', '', '', '',
    '','','','','','','');
  private apiAnswer: any;// http通信中使用
  private harders: any;//http通信中使用
  formModel: FormGroup;
  studentId: string;
  token:string = "";
  constructor(private routeInfo: ActivatedRoute, private router: Router, private studentService: StudentServiceService
  ,private http:HttpClient,private msg: NzMessageService) {
    var storage = window.localStorage;
    this.token = storage.getItem('token');
}

  ngOnInit() {

    let studentId : string = this.routeInfo.snapshot.params.id;
    this.studentId = studentId;
    if(studentId == '0') {

    } else {
      //异步根据id从服务器获取学生信息，获取后重置formModel

      this.studentService.getStudentById(studentId)
        .subscribe(resp => {
          const keys = resp.headers.keys();
          this.harders = keys.map(
            key => this.apiAnswer = {...resp.body}
          )
          if(this.apiAnswer.code == '100'){
            this.student = this.apiAnswer.extend.list.personList;
          }else{
            Swal.fire({
              type: 'error',
              title: 'Oops...',
              text: '没有成功获取到学生信息!',
              footer: '<a href>Why do I have this issue?</a>'
            })
          }
          this.formModel.reset({
            name: this.student.name,
            sex: this.student.sex,
            email: this.student.email,
            birthday: this.student.birthday,
            address: this.student.address,
            phone: this.student.phone,
            idCard: this.student.idCard,
            password:this.student.password,
            pinyin:this.student.pinyin
          })
        });
    }

    //formModel和模板中表单绑定
    let fb = new FormBuilder();
    this.formModel = fb.group(
      {
        name: ['', [Validators.required, Validators.minLength(1)]],
        sex: ['', [Validators.required]],
        phone: ['',mobileValidator],
        address: [''],
        idCard: [''],
        birthday: [''],
        email: [''],
        pinyin:[''],
        password:['']
      }
    )
  }

  /**
   * 取消，路由到学生信息管理主页面
   */
  cancel(){
//    Swal.fire('Hello world!');
    this.router.navigateByUrl('/studentManage');
  }
  /*
  * 保存，根据id判断是新建还是更新，注意这里 返回一下处理结果
  * */
  save() {


    if(this.studentId == '0') {
      this.student = this.formModel.value;
      this.student.picUrl = this.picUrl;
      this.studentService.addStudent(this.student)
        .subscribe(resp => {
          this.apiAnswer = resp;
          console.log("说好的100会返回")
          if(this.apiAnswer.code == '100'){
            Swal.fire({
              type: 'success',
              title: '添加成功',
              text: '添加了一条学生数据到数据库中',
              footer: '<a href>you are the best!</a>'
            })
            this.router.navigateByUrl("/studentManage");
          }else if(this.apiAnswer.code == '200'){
            Swal.fire({
              type: 'error',
              title: 'Oops...',
              text: 'Something went wrong!',
              footer: '<a href>Why do I have this issue?</a>'
            })}
        } );

    } else {
      this.student = this.formModel.value;
      this.student.id = this.studentId;
      this.studentService.updateStudent(this.student);
    }
  }
  //---------------上传文件----------------
  uploading = false;
  fileList: UploadFile[] = [];

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
          console.log("图片地址", this.picUrl)
        } else {
          Swal.fire({
            type: 'error',
            title: '添加失败',
          });
        }
      });
  }
}
