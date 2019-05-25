import { ApiService } from './../../../services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { State } from '../home-page/marketting/marketting.component';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  post: any;
  selectedFile: File = null;
  formPost: any;
  imageUrl = '../../../../assets/images/default.png';
  fileToUpload: File = null;
  constructor(private http: HttpClient, private api:ApiService) {
    this.post = {
      name: '',
      email: '',
      // social: {
      //   fb: '',
      //   twt: '',
      //   web: ''
      // },
      reKind: 'Căn hộ/ Chung cư',
      dob: '',
      diaChi: {
        tinh: 'Hồ Chí Minh',
        huyen: 'Quận 1',
        xa: 'Xã 1'
      },
      price: null,
      currency: 'VNĐ',
      reTradeKind: 'Bán',
      phone: '',
      houseAddress: '',
      housePhone: '',
      houseImages: '',
      bluePrints: ''
    };
   }
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
  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formPost.get('bluePrints').setValue(file);
    }
  }
  private prepareSave(): any {
    const input = new FormData();
    input.append('avatar', this.formPost.get('bluePrints').value);
    return input;
  }
  onUploadFile() {
     const formModel = this.prepareSave();
     console.log(formModel);
  }
  onSubmit(formValue) {
    // Do something awesome
    console.log(formValue);
    this.post = formValue;
    console.log( this.post);
    // throw Error('something go wrong');
  }

  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);

    // Show image preview
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    };
    reader.readAsDataURL(this.fileToUpload);
  }

  OnSubmit(Caption, Image) {
   this.api.postFile(Caption.value, this.fileToUpload).subscribe(
     data => {
       console.log(data);
       Caption.value = null;
       Image.value = null;
       this.imageUrl = this.imageUrl;
     }
   );
  }
}
