import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  @Input() dataSource: any;
  carouselOptions = {
    margin: 14,
    responsiveClass: true,
    nav: false,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: false,
    navSpeed: 700,
    autoplay: false,
    autoplayTimeout: 1000,
    autoplayHoverPause: true,
    loop: true,
    responsive: {
        0: {
            items: 1,
        },
        540: {
          items: 2,
        },
        720: {
            items: 2
        },
        1140: {
            items: 4
        }
    }
  };
  ngOnInit() {
  }
}
