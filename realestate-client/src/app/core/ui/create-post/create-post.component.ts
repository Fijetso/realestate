import { Component, OnInit } from '@angular/core';
import { State } from '../home-page/marketting/marketting.component';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  constructor() { }

  contact = {
    name: '',
    email: '',
    social: {
      fb: '',
      twt: '',
      email: ''
    },
    reKind: 'Căn hộ/ Chung cư',
    dob: '',
    diaChi: {
      tinh: 'Hồ Chí Minh',
      huyen: 'Quận 1',
      xa: 'Xã 1'
    }
  };
  reKinds: string[] = ['Căn hộ/ Chung cư', 'Nhà riêng', 'Đất nền'];
  states: State[] = [
    {name: 'Quận 1'},
    {name: 'Quận 2'},
    {name: 'Quận 3'},
    {name: 'Quận 4'},
    {name: 'Quận 5'},
    {name: 'Quận 6'},
    {name: 'Quận 7'},
    {name: 'Quận 8'},
    {name: 'Quận 9'},
    {name: 'Quận 10'},
    {name: 'Quận 11'},
    {name: 'Quận 12'},
    {name: 'Quận Bình Thạnh'},
    {name: 'Quận Gò Vấp'},
    {name: 'Quận Tân Bình'},
    {name: 'Quận Tân Phú'},
    {name: 'Quận Bình Tân'},
    {name: 'Huyện Nhà Bè'},
    {name: 'Huyện Bình Chánh'},
    {name: 'Huyện Hóc Môn'},
    {name: 'Huyện Củ Chi'},
    {name: 'Huyện Cần Giờ'},
  ];
  xaList = [
    'Xã 1',
    'Xã 2',
    'Xã 3',
    'Xã 4',
    'Xã 5',
    'Xã 6',
    'Xã 7',
    'Xã 8',
    'Xã 9',
    'Xã 10'
  ];
  ngOnInit() {
  }
  onSubmit(formValue) {
    // Do something awesome
    console.log(formValue);
    this.contact = formValue;
    console.log( this.contact);
    // throw Error('something go wrong');
  }
}
