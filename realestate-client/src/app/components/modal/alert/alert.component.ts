import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {
  favoriteSeason: string;
  seasons: string[] = ['Winter', 'Spring', 'Summer', 'Autumn'];
  isShow = true;
  constructor(
  ) { }
  ngOnInit() {
  }
  handleClick(){
    this.isShow = !this.isShow;
    console.log(this.isShow);
  }
}
