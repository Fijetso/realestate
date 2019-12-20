import {ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.scss']
})
export class NewsDetailComponent implements OnInit,OnDestroy {
  @Input()
  newsId: any;
  constructor(private activatedRoute : ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      this.newsId = +params['id'];
    })
   }

  ngOnInit() {
  }

  ngOnDestroy(): void {
  }
}
