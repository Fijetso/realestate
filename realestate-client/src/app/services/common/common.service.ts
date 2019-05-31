import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() { }

  public userObservable = new Subject();

  notifyUserDataChanged() {
    this.userObservable.next();
  }
}
