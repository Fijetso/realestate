import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api/api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.scss']
})
export class NewsListComponent implements OnInit {
  numbers: number[];
  newsList: any;
  newsList360: any;

  constructor( private api: ApiService, private route: ActivatedRoute, private router: Router) {
    this.numbers = Array(5).fill(0).map((x, i ) => i);
    this.api.getAllNews().subscribe(newsList => {
      this.newsList = newsList;
    });
  }

  ngOnInit() {
  }
  goToDetail(newsId) {
    this.router.navigate(['tin-tuc/chi-tiet/' + newsId]);
  }
}
