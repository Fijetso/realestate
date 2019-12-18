import { UserKind } from './../../../../model/user-kind/user-kind';
import { Address } from './../../../../model/address/address';
import { Router } from '@angular/router';
import { FormGroup,FormBuilder } from '@angular/forms';
import { RealEstate } from './../../../../model/real-estate/real-estate';
import { ApiService } from 'src/app/services/api/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-re',
  templateUrl: './add-re.component.html',
  styleUrls: ['./add-re.component.scss']
})
export class AddReComponent implements OnInit {

  realEstate: FormGroup;
  public imagePath;
  imgURL: any;
  message: string;
  address: Address;
  userKind: UserKind
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.realEstate = this.fb.group({
      id: 1,
      description: 'Mô tả',
      cost: 0,
      user: this.fb.group ({
        id: 0,
        name: 'Name',
        email: 'exampale@email.com',
        phone: '0985922740',
        password: 'password',
        birthdate:new Date("1995-19-11"),
        gender: true,
        userKind: this.fb.group({
          id:0
        })
      }),
      tradeKind: null,
      realEstateKind: null,
      address: this.fb.group({
        id: 0,
        detail: 'detail',
        ward: 0,
        district: 0,
        cityOrProvince: 0
      }),
      details: 'Mô tả chi tiết',
      realImages: null,
      bluePrints: null,
      booking: null
    });
  }

  save($event){
    this.api.createRE(this.realEstate.value);
    console.info(this.realEstate.value);
    // this.router.navigate(['./dang-tin']);
  }

  onChangeGender($event){ 
    console.info($event.target.value);
  }

  onChangeUserKind($event){ 
    console.info($event.target.value);
  }
  preview(files) {
    if (files.length === 0)
      return;
 
    let mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
 
    let reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }
}
