import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './composition/header/header.component';
import { ContentComponent } from './composition/content/content.component';
import { FooterComponent } from './composition/footer/footer.component';
import { MenuComponent } from './composition/menu/menu.component';
import { SidebarComponent } from './composition/sidebar/sidebar.component';
import { ManageComponent } from './modules/studentManage/manage/manage.component';
import { FormComponent } from './modules/studentManage/form/form.component';
import { HomeComponent } from './modules/homePage/home/home.component';
import { OrganizationManageCPComponent } from './modules/organizationManage/organization-manage/organization-manage-cp/organization-manage-cp.component';
import { OrganizationTreeComponent } from './modules/organizationManage/organization-manage/organization-manage-cp/organization-tree/organization-tree.component';
import { OrganizationMessageComponent } from './modules/organizationManage/organization-manage/organization-manage-cp/organization-message/organization-message.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { OrganizationManageComponent } from './modules/organizationManage/organization-manage/organization-manage.component';
import { RoleComponent } from './modules/organizationManage/organization-manage/role/role.component';
import { SchoolYearComponent } from './modules/organizationManage/organization-manage/school-year/school-year.component';
import { ScheduleComponent } from './modules/organizationManage/organization-manage/schedule/schedule.component';
import { FieldComponent } from './modules/organizationManage/organization-manage/field/field.component';
import { NewroleComponent } from './modules/organizationManage/organization-manage/role/newrole/newrole.component';
import { NewschoolyearComponent } from './modules/organizationManage/organization-manage/school-year/newschoolyear/newschoolyear.component';
import { NewfieldComponent } from './modules/organizationManage/organization-manage/field/newfield/newfield.component';


// import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { LoginComponent } from './start/login/login.component';
import { ClassTreeComponent } from './tools/class-tree/class-tree.component';
import { HttpClientJsonpModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_ICONS } from 'ng-zorro-antd';
import { IconDefinition } from '@ant-design/icons-angular';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import * as AllIcons from '@ant-design/icons-angular/icons';

import { NZ_I18N, en_US } from 'ng-zorro-antd';
import {HashLocationStrategy, LocationStrategy, PathLocationStrategy, registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';
import { UploadStudentsComponent } from './modules/studentManage/upload-students/upload-students.component';
import { StaffManageComponent } from './modules/staffManage/staff-manage/staff-manage.component';
import { RecruitStudentManageComponent } from './modules/recruitStudentManage/recruit-student-manage/recruit-student-manage.component';
import { DetailmanageComponent } from './modules/staffManage/staff-manage/detailmanage/detailmanage.component';
import { ClassManageComponent } from './modules/classManage/class-manage/class-manage.component';
import { CourseManageComponent } from './modules/courseManage/course-manage/course-manage.component';
import { CourseDetailComponent } from './modules/courseManage/course-manage/course-detail/course-detail.component';
import { SearchpipePipe } from './tools/pipes/searchpipe.pipe';
import { ClassFormComponent } from './modules/classManage/class-manage/class-form/class-form.component';
import { StaffFormComponent } from './modules/staffManage/staff-manage/staff-form/staff-form.component';
import { AaselectComponent } from './aaselect/aaselect.component';
import { CourseFormComponent } from './modules/courseManage/course-manage/course-form/course-form.component';
import { AalogindemoComponent } from './aalogindemo/aalogindemo.component';
import { MyStyleDirective } from './aadirectives/my-style.directive';
import { AauploadComponent } from './aaupload/aaupload.component';
import { Aauoload2Component } from './aauoload2/aauoload2.component';
import { IndexComponent } from './index/index/index.component';
import { EnrollmentPlanComponent } from './modules/enrollmentManage/enrollment-plan/enrollment-plan.component';
import { EnrollmentDetailComponent } from './modules/enrollmentManage/enrollment-detail/enrollment-detail.component';
import { ClueDetailComponent } from './modules/enrollmentManage/clue-detail/clue-detail.component';
import { EntryFormComponent } from './modules/enrollmentManage/entry-form/entry-form.component';
import { DatabaseManageComponent } from './modules/databaseManage/database-manage/database-manage.component';
import { FinanceManageComponent } from './modules/financeManage/finance-manage/finance-manage.component';
import { AatransferComponent } from './aatransfer/aatransfer.component';
import { FinanceDeatilComponent } from './modules/financeManage/finance-deatil/finance-deatil.component';
import { AatimeComponent } from './aatime/aatime.component';
import {TimeTableManageComponent} from './modules/timeTableManage/time-table-manage/time-table-manage.component';
import {DemoComponent} from './modules/timeTableManage/time-table-manage/calendar/demo/demo.component';
import {DemoUtilsComponent} from './modules/timeTableManage/time-table-manage/calendar/demo-utils/demo-utils.component';
import { AchievementManageComponent } from './modules/achievementManage/achievement-manage/achievement-manage.component';
import { AchievementDetailComponent } from './modules/achievementManage/achievement-detail/achievement-detail.component';
import { MyMessageManageComponent } from './modules/myMessageManage/my-message-manage/my-message-manage.component';
registerLocaleData(en);

const antDesignIcons = AllIcons as {
  [key: string]: IconDefinition;
};
const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key])

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    FooterComponent,
    MenuComponent,
    SidebarComponent,
    ManageComponent,
    FormComponent,
    HomeComponent,
    OrganizationManageCPComponent,
    OrganizationTreeComponent,
    OrganizationMessageComponent,
    OrganizationManageComponent,
    RoleComponent,
    SchoolYearComponent,
    ScheduleComponent,
    FieldComponent,
    NewroleComponent,
    NewschoolyearComponent,
    NewfieldComponent,
    DemoComponent,
    DemoUtilsComponent,
    LoginComponent,
    ClassTreeComponent,
    UploadStudentsComponent,
    StaffManageComponent,
    RecruitStudentManageComponent,
    DetailmanageComponent,
    ClassManageComponent,
    CourseManageComponent,
    CourseDetailComponent,
    SearchpipePipe,
    ClassFormComponent,
    StaffFormComponent,
    AaselectComponent,
    CourseFormComponent,
    AalogindemoComponent,
    MyStyleDirective,
    AauploadComponent,
    Aauoload2Component,
    IndexComponent,
    EnrollmentPlanComponent,
    EnrollmentDetailComponent,
    ClueDetailComponent,
    EntryFormComponent,
    DatabaseManageComponent,
    FinanceManageComponent,
    AatransferComponent,
    FinanceDeatilComponent,
    AatimeComponent,
    TimeTableManageComponent,
    AchievementManageComponent,
    AchievementDetailComponent,
    MyMessageManageComponent,
  ],
  imports: [
    HttpClientModule,
    HttpClientJsonpModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ScrollingModule,
    DragDropModule,
    NgZorroAntdModule,
    BrowserAnimationsModule,
     CalendarModule.forRoot({
        provide: DateAdapter,
       useFactory: adapterFactory
     })
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }, { provide: NZ_ICONS, useValue: icons },{ provide: LocationStrategy, useClass: PathLocationStrategy }],
  bootstrap: [AppComponent]
})
export class AppModule { }
