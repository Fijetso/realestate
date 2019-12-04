import { ActivatedRoute, ParamMap } from '@angular/router';
import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {
  districtId: any = null;
  data = null;
  // ckeConfig: any;
  // mycontent: string;
  // log = '';
  constructor(private api: ApiService, private route: ActivatedRoute) {
    // this.mycontent = `<p>My html content</p>`;
  }
  ngOnInit() {
   this.route.paramMap.subscribe( (params: ParamMap) => {
      this.districtId = params.get('id');
    });
  //  this.ckeConfig = {
  //     allowedContent: false,
  //     extraPlugins: 'divarea',
  //     forcePasteAsPlainText: true
  //   };
  }
  onChange($event: any): void {
    console.log('onChange');
    // this.log += new Date() + "<br />";
  }
}
