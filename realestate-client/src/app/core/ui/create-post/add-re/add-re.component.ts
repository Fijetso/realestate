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

  realEstate: FormGroup
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
        userKind: null
      }),
      tradeKind: null,
      realEstateKind: null,
      address: null,
      details: null,
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
}
