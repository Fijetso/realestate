import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-realestate-item",
  templateUrl: "./realestate-item.component.html",
  styleUrls: ["./realestate-item.component.scss"]
})
export class RealestateItemComponent implements OnInit {
  constructor() {}

  ngOnInit() {}

  main_pic = "https://picsum.photos/255/150";
  default_pic = "https://dummyimage.com/255x150/333/fff";
  offset = 100;
}
