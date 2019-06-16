import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ManageComponent} from './modules/studentManage/manage/manage.component';
import {HomeComponent} from './modules/homePage/home/home.component';
import {OrganizationManageCPComponent} from './modules/organizationManage/organization-manage/organization-manage-cp/organization-manage-cp.component';
import {OrganizationMessageComponent} from './modules/organizationManage/organization-manage/organization-manage-cp/organization-message/organization-message.component';
import {FormComponent} from './modules/studentManage/form/form.component';
import {OrganizationManageComponent} from './modules/organizationManage/organization-manage/organization-manage.component';
import {RoleComponent} from './modules/organizationManage/organization-manage/role/role.component';
import {SchoolYearComponent} from './modules/organizationManage/organization-manage/school-year/school-year.component';
import {ScheduleComponent} from './modules/organizationManage/organization-manage/schedule/schedule.component';
import {FieldComponent} from './modules/organizationManage/organization-manage/field/field.component';
import {NewroleComponent} from './modules/organizationManage/organization-manage/role/newrole/newrole.component';
import {LoginComponent} from './start/login/login.component';
import {ClassTreeComponent} from './tools/class-tree/class-tree.component';
import {UploadStudentsComponent} from './modules/studentManage/upload-students/upload-students.component';
import {StaffManageComponent} from './modules/staffManage/staff-manage/staff-manage.component';
import {DetailmanageComponent} from './modules/staffManage/staff-manage/detailmanage/detailmanage.component';
import {ClassManageComponent} from './modules/classManage/class-manage/class-manage.component';
import {CourseManageComponent} from './modules/courseManage/course-manage/course-manage.component';
import {CourseDetailComponent} from './modules/courseManage/course-manage/course-detail/course-detail.component';
import {ClassFormComponent} from './modules/classManage/class-manage/class-form/class-form.component';
import {StaffFormComponent} from './modules/staffManage/staff-manage/staff-form/staff-form.component';
import {CourseFormComponent} from './modules/courseManage/course-manage/course-form/course-form.component';
import {AalogindemoComponent} from './aalogindemo/aalogindemo.component';
import {AauploadComponent} from './aaupload/aaupload.component';
import {EnrollmentPlanComponent} from './modules/enrollmentManage/enrollment-plan/enrollment-plan.component';
import {EnrollmentDetailComponent} from './modules/enrollmentManage/enrollment-detail/enrollment-detail.component';
import {ClueDetailComponent} from './modules/enrollmentManage/clue-detail/clue-detail.component';
import {EntryFormComponent} from './modules/enrollmentManage/entry-form/entry-form.component';
import {IndexComponent} from './index/index/index.component';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {DatabaseManageComponent} from './modules/databaseManage/database-manage/database-manage.component';
import {Aauoload2Component} from './aauoload2/aauoload2.component';
import {FinanceManageComponent} from './modules/financeManage/finance-manage/finance-manage.component';
import {AatransferComponent} from './aatransfer/aatransfer.component';
import {FinanceDeatilComponent} from './modules/financeManage/finance-deatil/finance-deatil.component';
import {AatimeComponent} from './aatime/aatime.component';
import {TimeTableManageComponent} from './modules/timeTableManage/time-table-manage/time-table-manage.component';
import {AchievementManageComponent} from './modules/achievementManage/achievement-manage/achievement-manage.component';
import {AchievementDetailComponent} from './modules/achievementManage/achievement-detail/achievement-detail.component';


const routes: Routes = [
  {path: '', redirectTo: '/homePage', pathMatch: 'full'}, // pathMatch表示路径完全符合时才跳转
    {path: 'entryForm',component:EntryFormComponent},
    {path: 'recruitStudentsManage', component: EnrollmentPlanComponent},
    {path:'recruitStudentDetail/:id',component:EnrollmentDetailComponent},
    {path:'clueDetail/:id',component:ClueDetailComponent},
    {path: 'tree', component: ClassTreeComponent},
    {path: 'homePage', component: HomeComponent},
    {path: 'organizationalMessage/:id', component: OrganizationMessageComponent},
    {path: 'studentManage', component: ManageComponent},
    {path: 'organizationalManage', component: OrganizationManageComponent,
      children:[
        {path:'', component: OrganizationManageCPComponent,},
        {path:'organizational', component: OrganizationManageCPComponent,},
        {path:'role', component: RoleComponent,children:[
            {path:'newrole', component: NewroleComponent,}
          ]},
        {path:'year', component: SchoolYearComponent,},
        {path:'schedule', component: ScheduleComponent,},
        {path:'field', component: FieldComponent,}
      ]},
    {path: 'studentForm/:id', component: FormComponent},
    {path:'uploadStudents', component:UploadStudentsComponent},
    {path: 'staffManage', component:StaffManageComponent},
    {path:'staffdetail/:id', component: DetailmanageComponent},
    {path:'staffform/:id', component: StaffFormComponent},
    {path:'classManage', component: ClassManageComponent ,children:[
        {path:'', component:ClassFormComponent},
        {path:'newclass', component:ClassFormComponent}
      ]},
    {path:'newclass/:id', component:ClassFormComponent},
    {path:'courseManage', component: CourseManageComponent},
    {path:'courseDetail/:id', component: CourseDetailComponent},
    {path:'courseForm/:id', component: CourseFormComponent},
    {path:'test',component: AalogindemoComponent},
    {path:'testupload',component: Aauoload2Component},
  {path:'databaseManage',component: DatabaseManageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'entryForm',component:EntryFormComponent},
  {path:'financeManage',component:FinanceManageComponent},
  {path:'financeDetail/:id',component:FinanceDeatilComponent},
  {path:'timeTableManage',component:TimeTableManageComponent},
  {path:'achievementManage',component:AchievementManageComponent},
  {path:'AchievementDetail/:id',component:AchievementDetailComponent},
  {path:'aatransfer',component:AatransferComponent},
  {path:'aatime',component:AatimeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
