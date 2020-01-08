import { AddReComponent } from './core/ui/create-post/add-re/add-re.component';
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
import { CreatePostComponent } from './core/ui/create-post/create-post.component';
import { UserDetailComponent } from './core/ui/user-detail/user-detail.component';
import { ContactComponent } from './core/ui/contact/contact.component';
import { FengShuiComponent } from './core/ui/feng-shui/feng-shui.component';
const routes: Routes = [
  {
    path: '',
    component: RealestateListComponent,
    data: {
      title: 'Trang chủ',
      breadcrumb: 'Trang chủ'
    }
  },
  {
    path: 'mua/:slug',
    component: RealEstateDetailComponent,
    data: {
      title: 'Chi tiết bất động sản',
      breadcrumb: 'Chi tiết bất động sản'
    }
  },
  {
    path: 'tim-kiem',
    component: MapModuleComponent,
    data: {
      title: 'Tìm kiếm chi tiết các bất dộng sản',
      breadcrumb: 'Tìm kiếm chi tiết các bất dộng sản'
    }
  },
  {
    path: 'dang-nhap',
    component: LoginComponent,
    data: {
      title: 'Trang đăng nhập',
      breadcrumb: 'Trang đăng nhập'
    }
  },
  {
    path: 'yeu-thich',
    component: FavoriteComponent,
    data: {
      title: 'Trang bất động sản yêu thích',
      breadcrumb: 'Trang bất động sản yêu thích',
    }
  },
  {
    path: 'nguoi-dung',
    component: UserComponent,
    data: {
      title: 'Trang người dùng',
      breadcrumb: 'Trang người dùng'
    }
  },
  {
    path: 'tam',
    component: CreatePostComponent,
    data: {
      title: 'Trang bài đăng',
      breadcrumb: 'Trang bài đăng'
    }
  },
  {
    path: 'nguoi-dung/:id',
    component: UserDetailComponent,
    data: {
      title: 'Chi tiết người dùng',
      breadcrumb: 'Chi tiết người dùng'
    }
  },
  {
    path: 'dang-tin',
    component: AddReComponent,
    data: {
      title: 'Tạo bài đăng bất động sản',
      breadcrumb: 'Tạo bài đăng bất động sản',
    }
  },
  {
    path: 'lien-he',
    component: ContactComponent,
    data: { title: 'Thông tin liên hệ',
    breadcrumb: 'Thông tin liên hệ'
    }
  },
  {
    path: 'tim-kiem/nha-phong-thuy',
    component: FengShuiComponent,
    data: { title: 'Tìm nhà theo phong thuỷ',
    breadcrumb: 'Tìm nhà theo phong thuỷ'
    }
  },
  {
    path: 'tai-khoan',
    loadChildren: () => import('./core/ui/account/account.module').then(acc => acc.AccountModule)
  },
  {
    path: 'tin-tuc',
    loadChildren: () => import('./core/ui/news-module/news-module.module').then(news => news.NewsModuleModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('./core/ui/admin/admin.module').then(admin => admin.AdminModule)
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
export class AppRoutingModule { }
export const routingComponents = [
  RealestateListComponent,
  RealestateItemComponent
];
