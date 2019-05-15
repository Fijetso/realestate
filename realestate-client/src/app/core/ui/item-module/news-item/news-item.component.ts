import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['./news-item.component.scss']
})
export class NewsItemComponent implements OnInit {

  constructor() { }

  mainPic = 'https://picsum.photos/255/150';
  defaultpic = 'https://dummyimage.com/255x150/333/fff';
  offset = 100;

  ngOnInit() {
  }
}
