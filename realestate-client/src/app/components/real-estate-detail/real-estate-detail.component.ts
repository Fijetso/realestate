import { RealEstate } from 'src/app/model/real-estate/real-estate';
import { ApiService } from './../../services/api/api.service';
import { CommonService } from './../../services/common/common.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { OwlCarousel } from 'ngx-owl-carousel';
import { FormBuilder } from '@angular/forms';
import { GraphQueryService } from '../../services/graphql/graph-query.service';
import { ToastrService } from 'ngx-toastr';
import * as moment from 'moment';
import { AuthService, SocialUser } from 'angularx-social-login';

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
  viewedList = [];
  recentData = null;
  reSamePlace = null;
  reSameClass = null;
  requestInfo: any;
  user = JSON.parse(localStorage.getItem('loginInfo'));
  districtId: any;
  email: any;
  constructor(
    private route: ActivatedRoute,
    private api: ApiService,
    private common: CommonService,
    private fb: FormBuilder,
    private graphql: GraphQueryService,
    private toastr: ToastrService,
  ) {
    this.requestInfo = this.fb.group({
      name: this.user ? this.user.name : '',
      phone: '',
      email: '',
      dateStart: new Date(),
      dateEnd: new Date(),
      programCode: ''
    });
  }
  @ViewChild('owlElement', { static: true }) owlElement: OwlCarousel;
  carouselOptions = {
    margin: 14,
    responsiveClass: true,
    nav: false,
    navText: ['<i class="fas fa-angle-left fa-fw fa-2x"></i>', '<i class="fas fa-angle-right fa-fw fa-2x"></i>'],
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: true,
    navSpeed: 700,
    autoplay: true,
    autoplayTimeout: 2000,
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
      this.slug = params.get('slug');
      if (this.slug) {
        this.getTradeById(this.slug).subscribe(trade => {
          this.tradeData = trade;
          console.log(this.tradeData);
          if (!this.viewedList.find(item => item.id === this.slug)) {
            this.viewedList.push(trade);
            localStorage.setItem('viewedList', JSON.stringify(this.viewedList));
            this.recentData = JSON.parse(localStorage.getItem('viewedList'));
          }
          this.districtId = this.tradeData.address.district;
          this.getProvinceName(this.tradeData.address.cityOrProvince);
          this.getDistrictName(this.tradeData.address.cityOrProvince, this.tradeData.address.district);
          this.getWardName(this.tradeData.address.cityOrProvince, this.tradeData.address.district, this.tradeData.address.ward);
          this.getRealEstateSamePlace(this.tradeData.address.district);
          this.getAllRealEstateSameClass(this.tradeData.details.square);
        });
      }
    });
    this.recentData = JSON.parse(localStorage.getItem('viewedList'));
  }

  onPrevious() {
    this.owlElement.previous();
  }
  onNext() {
    this.owlElement.next();
  }

  getTradeById(tradeId: number) {
    return this.api.getTradeById(tradeId);
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
  getRealEstateSamePlace(districtId: number) {
    this.api.getTradeFromDistrict(districtId).subscribe(reList => {
      this.reSamePlace = reList;
    });
  }
  getAllRealEstateSameClass(square: number) {
    this.api.getAllRealEstate().subscribe(
      res => {
        console.log(this.getCurrentREClass(square));
        this.reSameClass = res.filter(item => this.getCurrentREClass(item.details.square) === this.getCurrentREClass(square));
      }
    );
  }
  getCurrentREClass(square: number): number {
    if (square >= 70) {
      return 1;
    } else if (square >= 60) {
      return 2;
    } else if (square >= 50) {
      return 3;
    } else { return 4; }
  }

  onSubmitRequest() {
    console.log(this.requestInfo.value);
    const name = this.requestInfo.get('name').value;
    const phone = this.requestInfo.get('phone').value;
    const email = this.requestInfo.get('email').value;
    const timeStart = moment(new Date(this.requestInfo.get('dateStart').value)).format('DD/MM/YYYY HH:mm:ss').toString();
    const timeEnd = moment(new Date(this.requestInfo.get('dateEnd').value)).format('DD/MM/YYYY HH:mm:ss').toString();
    const tradeId = +this.slug;
    this.graphql.saveBooking(name, phone, email, timeStart, timeEnd, tradeId).subscribe(res => {
      console.log(res.data.saveBooking);
      this.toastr.success('Thông tin đã được gửi đến người đăng', 'Đặt lịch thành công');
      return res && res.data;
    }, error => {
      this.toastr.error(error, 'Đặt lịch thất bại');
      console.error(error);
      return error;
    });
  }
}
