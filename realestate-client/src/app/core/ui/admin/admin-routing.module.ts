import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  {
    path: 'bdk', component : DashboardComponent, data: { title: 'Trang quản trị', breadcrumb: 'Trang quản trị' }
  },
  {
    path: '', component: LoginComponent, data: { title: 'Đăng nhập trang quản trị', breadcrumb: 'Đăng nhập' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
