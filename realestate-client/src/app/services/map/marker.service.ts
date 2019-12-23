import { ApiService } from './../api/api.service';
import { PopupService } from './popup.service';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as L from 'leaflet';
import * as LD from 'leaflet-draw';
// const data = {
//   "data": [
//     {
//       "name": "Tp Hồ Chí Minh",
//       "state": "Hồ Chí Minh",
//       "population": "8,993 triệu",
//       "img": "http://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp"
//     }
//   ]
// }
@Injectable({
  providedIn: 'root'
})
export class MarkerService {

  capitals: string = 'https://raw.githubusercontent.com/shawnbot/topogram/master/data/us-states.geojson';

  constructor(private http: HttpClient, private popupService: PopupService, private apiService: ApiService) {
  }

  markerHtmlStyles = `
  background-color: #FD784F;
  width: 3rem;
  height: 3rem;
  display: block;
  position: relative;
  border-radius: 3rem 3rem 0;
  transform: rotate(45deg);
  text-align: center;
  border: 1px solid #FFFFFF`;
  customIcon1 = L.divIcon({
    className: "my-custom-pin",
    iconAnchor: [0, 24],
    popupAnchor: [0, -36],
    html: `<div style="${this.markerHtmlStyles}">12</div>`
  })

  markerHtmlStyles2 = `
                      background-color: #FD784F;
                      width: 3rem;
                      height: 3rem;
                      display: block;
                      position: relative;
                      border-radius: 3rem 3rem 0;
                      transform: rotate(45deg);
                      text-align: center;
                      padding:1.1rem;
                      color:white;
                      font-size:1.5em;
                      border: 1px solid #FFFFFF`;
  getDataImage(trade) {
    return trade.realImages[0].imageLink
  }
  makeCapitalMarkers(map: L.Map): void {
    this.apiService.getAllRealEstate().subscribe((trades: any) => {
      // console.log(trades);
      // const dataImage= trades[0].realImages[0].imageLink;
      for (const trade of trades) {
        const lat = trade.coordinate.latitude;
        const lon = trade.coordinate.longitude;
        const popupData = {
          "description": trade.description,
          "cost": trade.cost,
          "kind": trade.realEstateKind.name,
          "img": trade.realImages[0].imageLink,
          "square": trade.details.square,
          "id": trade.id
        };
        // console.log(popupData);
        const marker = L.marker([lat, lon],
          {
            icon: L.divIcon({
              className: "my-custom-pin",
              iconAnchor: [0, 24],
              popupAnchor: [0, -36],
              html: `<div style="${this.markerHtmlStyles2}">${popupData.id}</div>`
            })
          })
          .bindTooltip(popupData.description)
          .bindPopup(this.popupService.makeCapitalPopup(popupData))
          .addTo(map);
      }
    });
    // const marker = L.marker([10.823099, 106.629662]).bindPopup(this.popupService.makeCapitalPopup(data))
    // .addTo(map);
  }
  makeCapitalCircleMarkers(map: L.Map): void {
    const circle = L.circleMarker([10.823099, 106.629662], { radius: 400 })
      // .bindPopup(this.popupService.makeCapitalPopup(data))
      .addTo(map);
    console.log(circle);
  }
  static ScaledRadius(val: number, maxVal: number): number {
    return 20 * (val / maxVal);
  }
}
