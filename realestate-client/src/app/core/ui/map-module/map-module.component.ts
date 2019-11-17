import { MarkerOptions, HereMapsManager } from 'ng2-heremaps';
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

  // latitude: number;
  // longitude: number;
  // zoom: number;
  // address: string;
  // private geoCoder;

  // @ViewChild('search')
  // public searchElementRef: ElementRef;

  private platform: any;
  @Input()
  private position :any ;
  @ViewChild("map")
  public mapElement: ElementRef;
  @Input()
  private markerOptions: MarkerOptions;
  @Input()
  private mapManager : HereMapsManager;
  @Input()
  private bubleOptions: BubbleOptions;
  
  constructor(
    // private mapsAPILoader: MapsAPILoader,
    // private ngZone: NgZone
  ) {
   this.position = {latitude: 10.8830014, longitude: 106.7817025}
   this.markerOptions = {title:'Tiêu đề', visible: true, label:"Nhãn",position:this.position}
    console.log(this.markerOptions);
   this.bubleOptions ={position: this.position}
   console.log(this.bubleOptions);
  }


  ngOnInit() {
    // //load Places Autocomplete
    // this.mapsAPILoader.load().then(() => {
    //   this.setCurrentLocation();
    //   this.geoCoder = new google.maps.Geocoder;

    //   const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
    //     types: ['address']
    //   });
    //   autocomplete.addListener('place_changed', () => {
    //     this.ngZone.run(() => {
    //       //get the place result
    //       const place: google.maps.places.PlaceResult = autocomplete.getPlace();

    //       //verify result
    //       if (place.geometry === undefined || place.geometry === null) {
    //         return;
    //       }

    //       //set latitude, longitude and zoom
    //       this.latitude = place.geometry.location.lat();
    //       this.longitude = place.geometry.location.lng();
    //       this.zoom = 12;
    //     });
    //   });
    // });
  }

  // Get Current Location Coordinates
  // private setCurrentLocation() {
  //   if ('geolocation' in navigator) {
  //     navigator.geolocation.getCurrentPosition((position) => {
  //       this.latitude = position.coords.latitude;
  //       this.longitude = position.coords.longitude;
  //       this.zoom = 8;
  //       this.getAddress(this.latitude, this.longitude);
  //     });
  //   }
  // }

  // markerDragEnd($event: MouseEvent) {
  //   console.log($event);
  //   this.latitude = $event.coords.lat;
  //   this.longitude = $event.coords.lng;
  //   this.getAddress(this.latitude, this.longitude);
  // }

  // getAddress(latitude, longitude) {
  //   this.geoCoder.geocode({ location: { lat: latitude, lng: longitude } }, (results, status) => {
  //     console.log(results);
  //     console.log(status);
  //     if (status === 'OK') {
  //       if (results[0]) {
  //         this.zoom = 12;
  //         this.address = results[0].formatted_address;
  //       } else {
  //         window.alert('No results found');
  //       }
  //     } else {
  //       window.alert('Geocoder failed due to: ' + status);
  //     }

  //   });
  // }
}
