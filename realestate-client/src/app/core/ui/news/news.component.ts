import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {
  numbers: number[];

  constructor() { 
    this.numbers = Array(5).fill(0).map((x,i)=>i);
  }

  ngOnInit() {
  }

}
