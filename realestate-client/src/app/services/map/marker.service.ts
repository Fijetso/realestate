import { ApiService } from './../api/api.service';
import { PopupService } from './popup.service';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as L from 'leaflet';

@Injectable({
  providedIn: 'root'
})
export class MarkerService {

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
    className: 'my-custom-pin',
    iconAnchor: [0, 24],
    popupAnchor: [0, -36],
    html: `<div style="${this.markerHtmlStyles}">12</div>`
  });

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
    return trade.realImages[0].imageLink;
  }
  makeCapitalMarkers(districtId: any, map: L.Map): void {
    this.apiService.getTradeFromDistrict(districtId).subscribe((trades: any) => {
      for (const trade of trades) {
        if (trade) {
          const lat = trade.coordinate.latitude;
          const lon = trade.coordinate.longitude;
          const popupData = {
          description: trade.description,
          cost: trade.cost,
          kind: trade.realEstateKind.name,
          img: trade.realImages[0].imageLink,
          square: trade.details.square,
          id: trade.id
        };
          const marker = L.marker([lat, lon],
          {
            icon: L.divIcon({
              className: 'my-custom-pin',
              iconAnchor: [0, 24],
              popupAnchor: [0, -36],
              html: `<div style="${this.markerHtmlStyles2}">${popupData.id}</div>`
            })
          })
          .bindTooltip(popupData.description)
          .bindPopup(this.popupService.makeCapitalPopup(popupData))
          .addTo(map);
        }
      }
    });
  }
}
