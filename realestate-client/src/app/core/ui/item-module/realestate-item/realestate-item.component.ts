import { GetDistrictNameFromIdPipe } from './../../../../ultility/pipe/get-district-name-from-id.pipe';
import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-realestate-item',
  templateUrl: './realestate-item.component.html',
  styleUrls: ['./realestate-item.component.scss']
})
export class RealestateItemComponent implements OnInit {
  realImages: any;
  mailText: string;
  constructor(private router: Router, private api: ApiService, private route: ActivatedRoute) {
    this.getDistrictFromProvinceId();
  }
  dataLoaded = false;
  @Input() dataItem: any;
  @Input() isAppraised: boolean;
  districtList: any;
  selectedItem: null;
  offset = 100;
  ngOnInit() {
    // console.log(this.dataItem);
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
    this.router.navigate(['mua', slug]);
  }
  mailTo(email) {
    this.mailText = 'mailto:' + email;
    window.location.href = this.mailText;
  }
}
