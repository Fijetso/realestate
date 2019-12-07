import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor() { }
  makeCapitalPopup(data: any): any {
    // console.log(data);
    return `` +
      `<div class="desc" style="color: #FD784F;
      font-size: 1.5em;
      font-weight: bold;">${data.description}</div>` +
      `<div class="square">Diện tích: ${ data.square }m<sup>2</sup></div>`+
      `<div class="cost">Giá: ${data.cost}</div>`+
      `<div class="kind">Loại: ${data.kind}</div>`+
      `<img src="${data.img}"/>`+
      `<a href="${'https://facebook.com/danh.thanh.418'}">Visit Facebook</a>`

  }
}
