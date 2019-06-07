import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  @Input() dataAppraised: any;
  @Input() isAppraised;
  @Input() dataTradingArea: any;
  @Input() isTradingArea;
  @Input() dataFavorite: any;
  @Input() isFavRE;
  @Input() dataHotRE: any;
  @Input() isHotRE;
  @Input() dataRecentRE: any;
  @Input() isRecentRE;
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
    loop: false,
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