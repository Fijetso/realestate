import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user/user';
import { HttpClient } from '@angular/common/http';

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
  // Post service

  // News service

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
