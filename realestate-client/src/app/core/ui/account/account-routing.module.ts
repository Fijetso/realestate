import { CollectionsComponent } from './collections/collections.component';
import { PostGalleryComponent } from './post-gallery/post-gallery.component';
import { AccountInfoComponent } from './account-info/account-info.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '', component : AccountInfoComponent, data: { title: 'Thông tin tài khoản', breadcrumb: 'Thông tin tài khoản' }
  },
  {
    path: 'tin-dang', component: PostGalleryComponent, data: { title: 'Tin đăng', breadcrumb: 'Tin đăng' }
  },
  {
    path: 'bo-suu-tap', component: CollectionsComponent, data: { title: 'Bộ sưu tập', breadcrumb: 'Bộ sưu tập' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
