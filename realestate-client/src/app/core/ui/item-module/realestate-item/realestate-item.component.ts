import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-realestate-item',
  templateUrl: './realestate-item.component.html',
  styleUrls: ['./realestate-item.component.scss']
})
export class RealestateItemComponent implements OnInit {
  constructor(private router: Router) {}

  mainPic = 'https://picsum.photos/255/150';
  defaultPic = 'https://dummyimage.com/255x150/333/fff';
  // defaultPic = '../../../../../assets/images/rolling.svg';
  offset = 100;
  ngOnInit() {

  }
  goToPage(url: string){
    this.router.navigate(['/' + url]);
  }
}
