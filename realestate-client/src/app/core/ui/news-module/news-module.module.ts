import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsModuleRoutingModule } from './news-module-routing.module';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailComponent } from './news-detail/news-detail.component';
import { NewsItemComponent } from './news-item/news-item.component';
import { MatCardModule, MatButtonModule } from '@angular/material';


@NgModule({
  declarations: [NewsListComponent, NewsDetailComponent, NewsItemComponent],
  imports: [
    CommonModule,
    NewsModuleRoutingModule,
    MatCardModule,
    MatButtonModule
  ],
  exports: [NewsListComponent, NewsDetailComponent, NewsItemComponent],
})
export class NewsModuleModule { }
