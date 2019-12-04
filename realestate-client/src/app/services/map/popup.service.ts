import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor() { }
  makeCapitalPopup(data: any): any {
    console.log(data);
    return `` +
      `<div>Capital: ${ data.data[0].name }</div>` +
      `<div>State: ${data.data[0].state }</div>`+
      `<div>Population: ${ data.data[0].population }</div>`+
      `<img src="${ data.data[0].img}"/>`
  }
}
