import { Component, OnInit } from '@angular/core';
import { newsData } from './fakedata';
@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.scss']
})
export class NewsDetailComponent implements OnInit {
  newsData: { id: number; composeDate: string; title: string; content: string; category: { id: number; name: string; }; }[];

  constructor() {
    this.newsData = newsData;
  }

  ngOnInit() {
  }

}
