import { environment } from './../../../environments/environment';
import { UserKind } from './../../model/user-kind/user-kind';
import { User } from './../../model/user/user';
import { RealEstate } from './../../model/real-estate/real-estate';
import { Address } from './../../model/address/address';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TradeKind } from '../../model/trade-kind/trade-kind';
import { RealEstateKind } from '../../model/real-estate-kind/real-estate-kind';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  rootURL = environment.api.rootURL;
  baseURL = this.rootURL + '/api/';
  userList: Observable<User[]>;
  userURL = this.baseURL + 'users';
  addressURL = this.baseURL + 'addresstree/provinces/';
  data = null;
  private reList: RealEstate[];


  constructor(private http: HttpClient) {
    this.reList = [
      { id: 1,
        description: 'Mô tả 1',
        cost: 90000000,
        user: {id: 1,
          name: 'Thang',
          email: 'danhthanh418@gmail.com',
          phone: '0975922740',
          password: '1234',
          birthdate: new Date('19/11/1995'),
          gender: true,
          userKind: null
        },
        tradeKind: null,
        realEstateKind: null,
        address: null,
        details: null,
        realImages: null,
        bluePrints: null,
        booking: null
      },
      { id: 2,
        description: 'Mô tả 2',
        cost: 100000000,
        user: {id: 1,
          name: 'Thanh',
          email: 'danhthanh448@gmail.com',
          phone: '',
          password: '12345678',
          birthdate: new Date('19/11/1995'),
          gender: true,
          userKind: null
        },
        tradeKind: null,
        realEstateKind: null,
        address: null,
        details: null,
        realImages: null,
        bluePrints: null,
        booking: null
      },
      { id: 3,
        description: 'Mô tả 3',
        cost: 75000000,
        user: {id: 1,
          name: 'Minh',
          email: 'minh.minh.123@gmail.com',
          phone: '0973476552',
          password: '6789',
          birthdate: new Date('19/03/1997'),
          gender: true,
          userKind: null
        },
        tradeKind: null,
        realEstateKind: null,
        address: null,
        details: null,
        realImages: null,
        bluePrints: null,
        booking: null
      }
    ];
  }

  getAllUserKind() {
    return this.http.get(this.baseURL + 'userkinds');
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
    return this.http.get<any>(this.addressURL);
  }

  getDistrictFromProvinceId(provinceId: number) {
    return this.http.get<any>(this.addressURL + provinceId + '/districts' );
  }

  getWardFromDistrictId(provinceId: number, districtId: number) {
    return this.http.get<any>(this.addressURL + provinceId + '/districts/' + districtId + '/wards');
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
    // return this.http.get<RealEstate>(this.baseURL + 'district/' + districtId + '/trades');
    return  this.http.get<RealEstate[]>(this.baseURL + 'trades');
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
    this.data = data;
  }
  getData() {
    return this.data;
  }

  getAllRE() {
    return this.reList;
  }

  getIndexRE(id: any) {
    for (let i = 0; i < this.reList.length; i++) {
      // tslint:disable-next-line: triple-equals
      if (this.reList[i].id == id) {
        return i;
      }
    }
    return -1;
  }

  getREById(id: any) {
    const index = this.getIndexRE(id);
    return this.reList[index];
  }

  createRE(re: RealEstate) {
    this.reList.push(re);
  }

  deleteRE(id: any) {
    const index = this.getIndexRE(id);
    const result = this.reList.splice(index, 1);
    // tslint:disable-next-line: no-console
    console.log(result);
  }

  updateRE(re: RealEstate) {
    const index = this.getIndexRE(re.id);
    this.reList[index].id = re.id;
    this.reList[index].description = re.description;
    this.reList[index].cost = re.cost;
    this.reList[index].user = re.user;
  }

  uploadImages(selectedFile, desc) {
    const uploadData = new FormData();
    uploadData.append('file', selectedFile, selectedFile ? selectedFile.name : null);
    uploadData.append('desc', desc);
    return this.http.post<any>(this.baseURL + 'image/upload', uploadData);
  }

  getNewsById(newsId: any) {
    return this.http.get<any>(this.baseURL + 'news/' + newsId);
  }

  getAllNews() {
    return this.http.get<any>(this.baseURL + 'news');
  }

  getTradeKind() {
    return this.http.get<TradeKind>(this.baseURL + 'tradekinds');
  }

  getREKind() {
    return this.http.get<RealEstateKind>(this.baseURL + 'realestatekinds');
  }
}
