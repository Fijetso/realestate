import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-hot-place-item',
  templateUrl: './hot-place-item.component.html',
  styleUrls: ['./hot-place-item.component.scss']
})
export class HotPlaceItemComponent implements OnInit {
  @Input() place: any;
  constructor() { }

  ngOnInit() {
  }

}