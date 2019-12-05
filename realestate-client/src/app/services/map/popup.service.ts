import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor() { }
  makeCapitalPopup(data: any): any {
    console.log(data);
    return `` +
      `<div>Phường: ${ 'Quận 3'}</div>` +
      `<div>Quận: ${'Tp Hồ Chí Minh' }</div>`+
      `<div>Diện tích: ${ '' }</div>`+
      `<img src="${data}"/>`+
      `<a href="${'https://facebook.com/danh.thanh.418'}">Visit Facebook</a>`

  }
}
