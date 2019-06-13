import { Address } from './../../model/address/address';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from 'src/app/model/user/user';
import { HttpClient } from '@angular/common/http';
import { RealEstate } from 'src/app/model/real-estate/real-estate';

@Injectable()
export class ApiService {
  rootURL = environment.api.rootURL;
  baseURL = this.rootURL + '/api/';
  userList: Observable<User[]>;
  userURL = this.baseURL + 'users';
  addressURL = this.baseURL + 'addresstree/provinces/';
  dataStorage = null;
  constructor(private http: HttpClient) {
  }

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
    return this.http.get<Address>(this.addressURL + provinceId + '/districts/' + districtId + '/wards');
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

  // GetTradeById service
  getTradeById(tradeId: number) {
    return this.http.get<RealEstate>(this.baseURL + 'trades/' + tradeId );
  }
  // Upload Image
  postFile(caption: string, fileToUpload: File) {
    const endpoint = this.baseURL + 'image/upload';
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);
    formData.append('desc', caption);
    return this.http
      .post(endpoint, formData);
  }
  getDistrictNameById(provinceId: number, disctrictId: number) {
    return this.http.get<any>(this.addressURL + provinceId + '/districts/' + disctrictId);
  }
  getProvincesById(provinceId: number) {
    return this.http.get<any>(this.addressURL  + provinceId);
  }
  // getWardById
  getWardById(provinceId: number, disctrictId: number, wardId: number) {
    return this.http.get<any>(this.addressURL  + provinceId + '/districts/' + disctrictId + '/wards/' + wardId);
  }
  getTradeFromDistrict(districtId: any) {
    return this.http.get<any>(this.baseURL + 'districts/' + districtId + '/trades');
  }
  setData(data: any) {
    this.dataStorage = data;
  }
  getData() {
    return this.dataStorage;
  }
  getLocation() {
    return this.http.get('http://api.ipapi.com/api/check?access_key=' + environment.ipapi.apiKey);
  }
}
