import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  myCarouselImages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20].map(
    i => `https://picsum.photos/id/${i}/800/400`
  );
  myCarouselOptions = {
    margin: 14,
    responsiveClass: true,
    nav: false,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: false,
    navSpeed: 700,
    responsive: {
        0: {
            items: 1,
        },
        540: {
          items: 2,
        },
        720: {
            items: 2,
            loop: false
        },
        1140: {
            items: 4,
            loop: false
        }
    }
  };
  ngOnInit() {}
}
