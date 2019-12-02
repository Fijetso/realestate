import { map } from 'rxjs/operators';
import { MarkerOptions, HereMapsManager,PolylineOptions } from 'ng2-heremaps';
// import { environment } from './../../../../environments/environment';
import { Component, OnInit, ElementRef, ViewChild, NgZone, Input } from '@angular/core';
import { BubbleOptions } from 'ng2-heremaps/src/interface/bubble-options';
// import { MapsAPILoader, MouseEvent } from '@agm/core';
declare var H: any;
@Component({
  selector: 'app-map-module',
  templateUrl: './map-module.component.html',
  styleUrls: ['./map-module.component.scss']
})


export class MapModuleComponent implements OnInit {

  public platform: any;
  @Input()
  public position :any ;
  @ViewChild("map",{static: true})
  public mapElement: ElementRef;
  @Input()
  public bubleInfor: MarkerOptions;
  @Input()
  public bubleOptions: BubbleOptions;
  @Input()
  public polyOntions : PolylineOptions;
  
  public isShowBuble : boolean;
  constructor(
    // private mapsAPILoader: MapsAPILoader,
    // private ngZone: NgZone
  ) {
   this.position = {latitude: 10.8830014, longitude: 106.7817025}
   this.bubleInfor = {title:'Test tiêu đề', visible: true, label:"Nhãn buble",position:this.position}
    console.log(this.bubleInfor);
   this.bubleOptions ={position: this.position}
   console.log(this.bubleOptions);
  }

  ngOnInit() {
  }

  showBubleInfor($event){
   this.isShowBuble = !this.isShowBuble;
  }
}
