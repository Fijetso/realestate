import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MouseEvent as AGMMouseEvent } from '@agm/core';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {
  constructor(private api: ApiService, private route: ActivatedRoute, private router: Router) {
    // this.mycontent = `<p>My html content</p>`;
  }
  districtId: any = null;
  data = null;
  title = null;
  // ckeConfig: any;
  // mycontent: string;
  // log = '';
  @ViewChild('myckeditor') ckeditor: any;
  zoom = 8;

  // initial center position for the map
  lat = 10.779118395736335;
  lng = 106.69120259693682;

  markers: marker[] = [
  {
    lat: 10.773858,
    lng: 106.815982,
    label: 'A',
    draggable: true
  },
  {
    lat: 10.738558,
    lng: 106.215982,
    label: 'B',
    draggable: false
  },
  {
    lat: 10.723858,
    lng: 106.895982,
    label: 'C',
    draggable: true
  }
  ];
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
  //  this.api.getLocation().subscribe(
  //    data => {
  //      console.log(data);
  //    }
  //  );
  }
  onChange($event: any): void {
    console.log('onChange');
    // this.log += new Date() + "<br />";
  }

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`);
  }
  mapClicked($event: AGMMouseEvent) {
    this.markers.push({
      lat: $event.coords.lat,
      lng: $event.coords.lng,
      draggable: true
    });
  }
  markerDragEnd(m: marker, $event: MouseEvent) {
    console.log('dragEnd', m, $event);
  }
}

interface marker {
  name?: string;
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
  visible?: boolean;
}
