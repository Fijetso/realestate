import { Component, OnInit, ViewChild } from '@angular/core';
import { OwlCarousel } from 'ngx-owl-carousel';

@Component({
  selector: 'app-real-estate-detail',
  templateUrl: './real-estate-detail.component.html',
  styleUrls: ['./real-estate-detail.component.scss']
})
export class RealEstateDetailComponent implements OnInit {
  constructor() {}
  @ViewChild('owlElement') owlElement: OwlCarousel;
  myCarouselImages = [
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    17,
    18,
    19,
    20
  ].map(i => `https://picsum.photos/id/${i}/800/400`);
  myCarouselOptions = {
    margin: 14,
    responsiveClass: true,
    nav: false,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: true,
    navSpeed: 700,
    autoplay: true,
    autoplayTimeout: 1500,
    autoplayHoverPause: true,
    loop: true,
    // animateOut: 'slideOutDown',
    // animateIn: 'flipInX',
    // stagePadding:30,
    responsive: {
      0: {
        items: 1
      },
      540: {
        items: 1
      },
      720: {
        items: 1
      },
      1140: {
        items: 1
      }
    }
  };

  ngOnInit() {}

  onPrevious() {
    this.owlElement.previous();
  }
  onNext() {
    this.owlElement.next();
  }
}
