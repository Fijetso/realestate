import { UserComponent } from './components/user/user.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { LoginComponent } from './core/ui/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RealestateListComponent } from './components/home/realestate-list.component';
import { RealestateItemComponent } from './core/ui/item-module/realestate-item/realestate-item.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MapModuleComponent } from './core/ui/map-module/map-module.component';
import { RealEstateDetailComponent } from './components/real-estate-detail/real-estate-detail.component';
import { RegisterComponent } from './core/ui/register/register.component';
import { CreatePostComponent } from './core/ui/create-post/create-post.component';
import { UserDetailComponent } from './core/ui/user-detail/user-detail.component';

const routes: Routes = [
  // { path: '', redirectTo: 'trang-chu', pathMatch: 'full' },
  {
    path: '',
    component: RealestateListComponent,
    data: { title: 'Tiêu đề trang chủ' }
  },
  {
    path: 'mua',
    component: RealEstateDetailComponent,
    data: { title: 'Chi tiết bất động sản' }
  },
  {
    path: 'ban-do',
    component: MapModuleComponent,
    data: { title: 'Trang bản đồ' }
  },
  {
    path: 'dang-nhap',
    component: LoginComponent,
    data: { title: 'Trang đăng nhập' }
  },
  {
    path: 'yeu-thich',
    component: FavoriteComponent,
    data: { title: 'Trang bất động sản yêu thích' }
  },
  {
    path: 'dang-ky',
    component: RegisterComponent,
    data: { title: 'Trang đăng ký' }
  },
  {
    path: 'nguoi-dung',
    component: UserComponent,
    data: { title: 'Trang người dùng' }
  },
  {
    path: 'dang-tin',
    component: CreatePostComponent,
    data: { title: 'Trang bài đăng' }
  },
  {
    path: 'nguoi-dung/:id',
    component: UserDetailComponent,
    data: { title: 'Chi tiết người dùng' }
  },
  {
    path: '**',
    component: PageNotFoundComponent,
    data: { title: 'Trang không tìm thấy' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [
  RealestateListComponent,
  RealestateItemComponent
];
