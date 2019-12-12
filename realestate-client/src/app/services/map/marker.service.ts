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
        const popupData = {"description":trade.description, 
        "cost":trade.cost, 
        "kind":trade.realEstateKind.name,
        "img":trade.realImages[0].imageLink,
        "square":trade.details.square
      };
        // console.log(popupData);
        const marker = L.marker([lat, lon]).bindPopup(this.popupService.makeCapitalPopup(popupData))
          .addTo(map);
        // console.log(lat,lon);
      }
    });
    // const marker = L.marker([10.823099, 106.629662]).bindPopup(this.popupService.makeCapitalPopup(data))
    // .addTo(map);
  }
  makeCapitalCircleMarkers(map: L.Map): void {
    // this.http.get(this.capitals).subscribe((res: any) => {

    //   // Find the maximum population to scale the radii by.
    //   const maxVal = Math.max(...res.features.map(x => x.properties.population), 0);

    //   for (const c of res.features) {
    //     const lat = c.geometry.coordinates[0];
    //     const lon = c.geometry.coordinates[1];
    //     const circle = L.circleMarker([lon, lat], {
    //       radius: MarkerService.ScaledRadius(c.properties.population, maxVal)
    //     }).addTo(map);
    //   }
    // });
    const circle = L.circleMarker([10.823099, 106.629662], { radius: 400 })
      // .bindPopup(this.popupService.makeCapitalPopup(data))
      .addTo(map);
    console.log(circle);
  }
  static ScaledRadius(val: number, maxVal: number): number {
    return 20 * (val / maxVal);
  }
}
