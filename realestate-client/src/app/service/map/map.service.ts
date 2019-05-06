import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root"
})
export class MapService {
  latittude: string;
  longitude: string;
  Location: {
    lattitude: string;
    longitude: string;
  };
  ipAddress: { ip: string };

  constructor(private http: HttpClient) {
    // this.getIpAddress();
    this.getLocation();
  }

  getIpAddress(){
    this.http.get<{ ip: string }>("https://jsonip.com").subscribe(data => {
      console.log("th data", data);
      this.ipAddress = data;
    });
  }
  getLocation(){
    return this.http.get<Location>("http://api.ipapi.com/api/check?access_key ="+environment.ipapi.apiKey);
  }
}
