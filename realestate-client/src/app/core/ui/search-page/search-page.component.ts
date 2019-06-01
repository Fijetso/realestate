import { ActivatedRoute, ParamMap } from '@angular/router';
import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {
  districtId : any = null;
  data = null;
  constructor(private api: ApiService,private route: ActivatedRoute) { }

  ngOnInit() {
   this.data= this.api.getData();
    this.route.paramMap.subscribe( (params: ParamMap) => {
      this.districtId= params.get('id');
      console.log(this.districtId);
    })
  }

}
