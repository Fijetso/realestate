import { RealEstate } from 'src/app/model/real-estate/real-estate';
import { ApiService } from './../../services/api/api.service';
import { CommonService } from './../../services/common/common.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { OwlCarousel } from 'ngx-owl-carousel';

@Component({
  selector: 'app-real-estate-detail',
  templateUrl: './real-estate-detail.component.html',
  styleUrls: ['./real-estate-detail.component.scss']
})
export class RealEstateDetailComponent implements OnInit {
  slug: any;
  reId: any;
  tradeData: any;
  provinceName: string;
  disctrictName: string;
  wardName: string;
  constructor(private route: ActivatedRoute, private api: ApiService, private common: CommonService) {}
  @ViewChild('owlElement') owlElement: OwlCarousel;
  // myCarouselImages = [
  //   1,
  //   2,
  //   3,
  //   4,
  // ].map(i => `https://picsum.photos/id/${i}/800/400`);
  carouselOptions = {
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

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      // this.slug = this.common.changeToSlug(params.get('slug'));
      // this.reId = params.get('id');
      this.slug = params.get('slug');
      if (this.slug) {
        this.getTradeById(this.slug);
      }
    });
  }

  onPrevious() {
    this.owlElement.previous();
  }
  onNext() {
    this.owlElement.next();
  }

  getTradeById(tradeId: number) {
    this.api.getTradeById(tradeId).subscribe(trade => {
      this.tradeData = trade;
      this.getProvinceName(this.tradeData.address.cityOrProvince);
      this.getDistrictName(this.tradeData.address.cityOrProvince, this.tradeData.address.district);
      this.getWardName(this.tradeData.address.cityOrProvince, this.tradeData.address.district, this.tradeData.address.ward);
    });
  }
  getDistrictName(provinceId: number, districtId: number) {
    this.api.getDistrictNameById(provinceId, districtId).subscribe(district => {
      this.disctrictName = district.nameWithType;
      // console.log( this.disctrictName);
    });
  }
  getProvinceName(provinceId: number) {
    this.api.getProvincesById(provinceId).subscribe(province => {
      this.provinceName = province.nameWithType;
      // console.log( this.provinceName);
    });
  }
  getWardName(provinceId: number, districtId: number, wardId: number) {
    this.api.getWardById(provinceId, districtId, wardId).subscribe(ward => {
      this.wardName = ward.nameWithType;
      // console.log( this.wardName);
    });
  }
}
