import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-realestate-item',
  templateUrl: './realestate-item.component.html',
  styleUrls: ['./realestate-item.component.scss']
})
export class RealestateItemComponent implements OnInit {
  constructor() {}

  mainPic = 'https://picsum.photos/255/150';
  defaultpic = 'https://dummyimage.com/255x150/333/fff';
  offset = 100;

  ngOnInit() {}
}
