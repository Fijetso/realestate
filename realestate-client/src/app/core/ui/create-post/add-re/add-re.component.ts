import { UserKind } from './../../../../model/user-kind/user-kind';
import { Address } from './../../../../model/address/address';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ApiService } from 'src/app/services/api/api.service';
import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GraphQueryService } from './../../../../services/graphql/graph-query.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-re',
  templateUrl: './add-re.component.html',
  styleUrls: ['./add-re.component.scss']
})
export class AddReComponent implements OnInit, AfterViewInit {
  realEstate: FormGroup;
  public imagePath;
  imgURL: any;
  message: string;
  address: Address;
  userKinds: UserKind[];
  selectedFile: File;
  @Input()
  height: number;
  protected map;
  protected curLat;
  protected curLong;
  markerHtmlStyles2: any;
  userInfo: any;
  startDate: any;
  realImageLink: any;
  reKinds: any;
  tradeKinds: any;
  districtList: any;
  wardList: any;

  constructor(
    private api: ApiService,
    private fb: FormBuilder,
    private router: Router,
    private graphql: GraphQueryService,
    private toastr: ToastrService
  ) {
    this.api.getAllUserKind().subscribe(res => {
      // console.log(res);
      this.userKinds = res as UserKind[];
    });

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
      });
    }
    this.getREKind();
    this.getTradeKinds();
    this.getAllDistrict(79);
    this.getAllWardFromDistrict(79, 774);
    this.getUserKinds();
    this.userInfo = JSON.parse(localStorage.getItem('loginInfo'));
    this.startDate = new Date(1997, 10, 19);
  }

  ngAfterViewInit(): void {
  }
  ngOnInit() {
    this.height = 500;
    this.realEstate = this.fb.group({
      description: 'Mô tả về bất động sản của bạn',
      cost: 2000000000,
      user: this.fb.group({
        id: this.userInfo ? this.userInfo.id : null,
        name: this.userInfo ? this.userInfo.name : null,
        email: this.userInfo ? this.userInfo.email : null,
        phone: '',
        password: '',
        dob: new Date(1997, 10, 19),
        gender: true,
        userKind: 1
      }),
      tradeKind: 1,
      realEstateKind: 1,
      address: this.fb.group({
        detail: '',
        ward: 27313,
        district: 774,
        cityOrProvince: 79
      }),
      details: this.fb.group({
        length: 9,
        width: 4,
        square: 37,
        direction: 'Đông',
        floors: '1 trệt + 1 lầu',
        legalDocuments: 'Sổ hồng riêng',
        bathrooms: 1,
        bedrooms: 2,
        utilities: 'Gần chợ, siêu thị, gần trường học',
        others: 'Bán nhà riêng quận 5, đang cho thuê 6tr/tháng sổ hồng chính chủ'
      }),
      coordinate: this.fb.group({
        longitude: 106.6762428,
        latitude: 10.756833
      }),
      realImages: this.fb.group({
        description: 'Mô tả hình ảnh',
        imageLink: ''
      }),
      bluePrints: null,
      booking: null
    });
  }

  // Init map


  // Save to create post
  save($event) {
    if (this.selectedFile) {
      const desc = this.realEstate.get('realImages.description').value;
      this.api.uploadImages(this.selectedFile, desc).subscribe(res => {
        this.realImageLink = res;
        this.realEstate.get('realImages.imageLink').setValue(this.realImageLink.imageLink);
        console.log(this.realEstate.value);

        const description = this.realEstate.get('description').value;
        const cost = this.realEstate.get('cost').value;
        const userId = this.userInfo.id;
        const realEstateKindId = this.realEstate.get('realEstateKind').value;
        const tradeKindId = this.realEstate.get('tradeKind').value;
        const detailAddress = this.realEstate.get('address.detail').value;
        const wardId = this.realEstate.get('address.ward').value;
        const width = this.realEstate.get('details.width').value;
        const square = this.realEstate.get('details.square').value;
        const direction = this.realEstate.get('details.direction').value;
        const floors = this.realEstate.get('details.floors').value;
        const legalDocuments = this.realEstate.get('details.legalDocuments').value;
        const bathrooms = this.realEstate.get('details.bathrooms').value;
        const bedrooms = this.realEstate.get('details.bedrooms').value;
        const utilities = this.realEstate.get('details.utilities').value;
        const others = this.realEstate.get('details.others').value;
        const longitude = this.realEstate.get('coordinate.longitude').value;
        const latitude = this.realEstate.get('coordinate.latitude').value;
        const realImages = new Array (this.realEstate.get('realImages.imageLink').value);

        this.saveTrade(description, cost, userId, realEstateKindId,
          tradeKindId, detailAddress, wardId, length, width, square,
          direction, floors, legalDocuments, bathrooms, bedrooms,
          utilities, others, longitude, latitude, realImages);
      });
    }
  }
  saveTrade(description, cost, userId, realEstateKindId,
            tradeKindId, detailAddress, wardId, length, width, square,
            direction, floors, legalDocuments, bathrooms, bedrooms,
            utilities, others, longitude, latitude, realImages) {
    this.graphql.saveTrade(description, cost, userId, realEstateKindId, tradeKindId,
      detailAddress, wardId, length, width, square, direction,
      floors, legalDocuments, bathrooms, bedrooms, utilities,
      others, longitude, latitude, realImages)
      .subscribe(res => {
        this.toastr.success('Mã bài đăng' + res.data.saveTrade.id, 'Đăng tin thành công');
        console.log(res);
      }, error => {
        this.toastr.error(error, 'Đăng tin thất bại');
        console.error(error);
      });
  }

  onChangeGender($event) {
    // tslint:disable-next-line: no-console
    console.log($event.target.value);
  }

  onChangeUserKind($event) {
    // tslint:disable-next-line: no-console
    console.log($event.target.value);
  }
  preview(event) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    this.imagePath = event.target.files[0];
    reader.readAsDataURL(event.target.files[0]);
    // tslint:disable-next-line: variable-name
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    };
  }

  onSelectFile() {
    const element: HTMLElement = document.querySelector('input[type="file"]') as HTMLElement;
    element.click();
  }

  getREKind() {
    this.api.getREKind().subscribe(reKinds => {
      this.reKinds = reKinds;
      // console.log(this.reKinds);
    });
  }

  getTradeKinds() {
    this.api.getTradeKind().subscribe(tradeKinds => {
      this.tradeKinds = tradeKinds;
      // console.log(this.tradeKinds);
    });
  }

  getAllDistrict(provinceId) {
    this.api.getDistrictFromProvinceId(provinceId).subscribe(districtList => {
      this.districtList = districtList;
    });
  }

  getAllWardFromDistrict(provinceId, districtId) {
    this.api.getWardFromDistrictId(provinceId, districtId).subscribe(wardList => {
      // console.log(wardList);
      this.wardList = wardList;
    });
  }

  onChangeDistrict() {
    const district = this.realEstate.get('address').get('district').value;
    this.getAllWardFromDistrict(79, district);
  }

  getUserKinds() {
    this.api.getAllUserKind().subscribe(userKinds => {
      this.userKinds = userKinds as UserKind[];
    });
  }
}
