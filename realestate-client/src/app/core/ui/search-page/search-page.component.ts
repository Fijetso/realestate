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
  name = 'ng2-ckeditor';
  ckeConfig: any;
  mycontent: string;
  log = '';
  @ViewChild('myckeditor') ckeditor: any;
  constructor(private api: ApiService, private route: ActivatedRoute) {
    this.mycontent = `<p>My html content</p>`;
  }
  ngOnInit() {
   this.data = this.api.getData();
   this.route.paramMap.subscribe( (params: ParamMap) => {
      this.districtId = params.get('id');
      console.log(this.districtId);
    });
   this.ckeConfig = {
      allowedContent: false,
      extraPlugins: 'divarea',
      forcePasteAsPlainText: true
    };
  }
  onChange($event: any): void {
    console.log('onChange');
    // this.log += new Date() + "<br />";
  }
}
