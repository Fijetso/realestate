import { Component, OnInit } from '@angular/core';
import { newsData } from './fakedata';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ApiService } from 'src/app/services/api/api.service';
@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.scss']
})
export class NewsDetailComponent implements OnInit {
  newsData: any;
  newsId: any;

  constructor(private route: ActivatedRoute, private api: ApiService) {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.newsId = params.get('slug');
      if (this.newsId) {
        this.api.getNewsById(this.newsId).subscribe(data => {
          this.newsData = data;
          console.log(data);
        });
      }
    });
  }

  ngOnInit() {
  }

}
