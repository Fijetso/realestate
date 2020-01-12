import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsModuleRoutingModule } from './news-module-routing.module';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailComponent } from './news-detail/news-detail.component';
import { NewsItemComponent } from './news-item/news-item.component';
import { MatCardModule, MatButtonModule, MatDividerModule } from '@angular/material';
import { FilterNewsPipe } from '../../../ultility/pipe/filter-news/filter-news.pipe';
import { SafeHtmlPipe } from '../../../ultility/pipe/safe-html/safe-html.pipe';


@NgModule({
  declarations: [NewsListComponent,
    NewsDetailComponent,
    NewsItemComponent,
    FilterNewsPipe,
    SafeHtmlPipe
  ],
  imports: [
  CommonModule,
    NewsModuleRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule
  ],
  exports: [NewsListComponent,
    NewsDetailComponent,
    NewsItemComponent,
    FilterNewsPipe,
    SafeHtmlPipe
  ],
})
export class NewsModuleModule { }
