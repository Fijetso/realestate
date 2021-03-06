import { ToastrService } from 'ngx-toastr';
import { GetDistrictNameFromIdPipe } from './../../../../ultility/pipe/get-district-name-from-id.pipe';
import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Input, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from './../../../../services/data/data.service';

@Component({
  selector: 'app-realestate-item',
  templateUrl: './realestate-item.component.html',
  styleUrls: ['./realestate-item.component.scss']
})
export class RealestateItemComponent implements OnInit {
  realImages: any;
  mailText: string;
  constructor(private router: Router, private api: ApiService, private route: ActivatedRoute, private data: DataService) {
    this.getDistrictFromProvinceId();
  }
  dataLoaded = false;
  @Input() dataItem: any;
  @Input() isAppraised: boolean;
  @Output() public childEvent = new EventEmitter();
  districtList: any;
  selectedItem: null;
  offset = 100;
  loginInfo = JSON.parse(localStorage.getItem('userInfor'));
  ngOnInit() {
  }
  getDistrictFromProvinceId() {
   this.api.getDistrictFromProvinceId(79).subscribe(res => {
     this.districtList = res;
     this.dataLoaded = true;
   });
  }
  goToPage(url: string) {
    this.router.navigate(['/' + url]);
  }
  onSelect(slug: any) {
    this.router.navigate(['chi-tiet', slug]);
  }

  addToFavList(dataItem) {
    this.data.addFavList(dataItem);
  }
}
