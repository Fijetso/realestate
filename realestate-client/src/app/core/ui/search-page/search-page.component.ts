import { ActivatedRoute, ParamMap, Router } from '@angular/router';
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
  title = null;
  // ckeConfig: any;
  // mycontent: string;
  // log = '';
  @ViewChild('myckeditor') ckeditor: any;
  constructor(private api: ApiService, private route: ActivatedRoute,private router: Router) {
    // this.mycontent = `<p>My html content</p>`;
  }
  ngOnInit() {
  //  this.route.paramMap.subscribe( (params: ParamMap) => {
  //     this.districtId = params.get('id');
  //   });
   if (history.state.data) {
    this.data = history.state.data;
    localStorage.setItem('history', JSON.stringify(history.state));
    console.log(history.state);
    this.title = history.state.title;
   } else {
    console.log(JSON.parse(localStorage.getItem('history')));
    this.data = JSON.parse(localStorage.getItem('history')).data;
    this.title = JSON.parse(localStorage.getItem('history')).title;
   }
  }
  onChange($event: any): void {
    console.log('onChange');
    // this.log += new Date() + "<br />";
  }
}
