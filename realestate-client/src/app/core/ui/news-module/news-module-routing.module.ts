import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailComponent } from './news-detail/news-detail.component';


const routes: Routes = [
  {
    path: '', component : NewsListComponent, data: { title: 'Tin tức bất động sản' , breadcrumb: 'Tin tức'}
  },
  {
    path: 'chi-tiet/:slug', component: NewsDetailComponent, data: { title: 'Chi tiết bất động sản', breadcrumb: 'Chi tiết tin tức' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NewsModuleRoutingModule { }