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
  reKinds: string[] = ['Căn hộ/ Chung cư', 'Nhà riêng', 'Đất nền'];
  province: any = null;
  districtList: any = null;
  wardList: any = null;
  constructor(private api: ApiService) {
    this.post = {
      name: '',
      email: '',
      reKind: 'Căn hộ/ Chung cư',
      dob: '',
      diaChi: {
        tinh: 79,
        huyen: 762,
        xa: 26800
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

  ngOnInit() {
    this.getProvince(79);
  }
  getProvince(provinceId: number) {
    this.api.getProvincesById(provinceId).subscribe(province => {
      this.province = province;
      this.getDistrictFromProvince(provinceId);
    });
  }

  getDistrictFromProvince(provinceId: number) {
    this.api.getDistrictFromProvinceId(provinceId).subscribe(districtList => {
      this.districtList = districtList;
    });
  }
  getWardFromDisctrictid(provinceId: number, districtId: number) {
    this.api.getWardFromDistrictId(provinceId, districtId).subscribe(wardList => {
      this.wardList = wardList;
    });
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
