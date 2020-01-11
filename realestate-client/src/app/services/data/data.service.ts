import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RealEstate } from './../../model/real-estate/real-estate';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private isLoggedIn = new BehaviorSubject(true);
  currentLogin = this.isLoggedIn.asObservable();
  private user = new BehaviorSubject(null);
  currentUser = this.user.asObservable();

  private favList: BehaviorSubject<any[]> = new BehaviorSubject(localStorage.getItem('favS') ?
  JSON.parse(localStorage.getItem('favS')) :
   []);
  currentFavList = this.favList.asObservable();
  constructor(private toasrt: ToastrService) {
  }

  changeLoginState(loginState: boolean) {
    this.isLoggedIn.next(loginState);
  }

  changeCurrentUser(userState: any ) {
    this.user.next(userState);
  }
  addFavList(favItem: RealEstate) {
    const currentValue = this.favList.value;
    this.toasrt.success('Đã thêm vào danh sách yêu thích', 'Thêm vào danh sách yêu thích');
    const updatedValue  = [...currentValue, favItem];
    this.favList.next(updatedValue);
    this.currentFavList.subscribe(newValue => {
      localStorage.setItem('favS', JSON.stringify(newValue));
      console.log(newValue);
    });
  }
  deleteFavList() {
    this.favList = null;
  }
}