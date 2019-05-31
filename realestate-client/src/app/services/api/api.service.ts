import { Address } from './../../model/address/address';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user/user';
import { HttpClient } from '@angular/common/http';
import { RealEstate } from 'src/app/model/real-estate/real-estate';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {
  }
  rootURL = environment.api.rootURL;
  baseURL = this.rootURL + '/api/';
  userList: Observable<User[]>;
  userURL = this.baseURL + 'users';
  addressURL = this.baseURL + 'addresstree/provinces/';
  // User service
  createUser(user: User) {
    return this.http.post<User>(this.userURL, user);
  }
  getAllUser() {
    return this.http.get<User[]>(this.userURL);
  }

  getUserById(id: number) {
    return this.http.get<User>(this.userURL + '/' + id);
  }

  deleteUser(id: number) {
    return this.http.delete<User>(this.userURL + '/' + id);
  }

  updateUser(user: User, id: number) {
    return this.http.put<User>(this.userURL + '/' + id, user);
  }
  // RealEstate service
  getAllRealEstate() {
    return this.http.get<RealEstate[]>(this.baseURL + 'trades');
  }
  // Address service
  getProvinces() {
    return this.http.get<Address>(this.addressURL);
  }

  getDistrictFromProvinceId(provinceId: number) {
    return this.http.get<Address>(this.addressURL + provinceId + '/districts' );
  }

  getWardFromDistrictId(provinceId: number, districtId: number) {
    return this.http.get<Address>(this.addressURL + provinceId + '/district' + districtId + '/ward');
  }
  // get Favorite trade order by fav count
  getFavRealEstate() {
    return this.http.get<RealEstate>(this.baseURL + 'trades');
  }
  // get hot real estate : order by view count;
  getHotRealEstate() {
    return this.http.get<RealEstate>(this.baseURL + 'trades');
  }
  // get Trade by District
  findTradeByDistrict(districtId: number) {
    return this.http.get<RealEstate>(this.baseURL + 'district/' + districtId + '/trades');
  }

  // Request service

  // Upload Image
  postFile(caption: string, fileToUpload: File) {
    const endpoint = this.baseURL + 'image/upload';
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);
    formData.append('desc', caption);
    return this.http
      .post(endpoint, formData);
  }
}
