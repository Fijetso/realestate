import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trading-area-section',
  templateUrl: './trading-area-section.component.html',
  styleUrls: ['./trading-area-section.component.scss']
})
export class TradingAreaSectionComponent implements OnInit {
  hotPlaceList = [
    {
    id: 1,
    img: 'https://picsum.photos/id/11/600',
    name: 'Quận Bình Thạnh'},
    {
    id: 2,
    img: 'https://picsum.photos/id/12/600',
    name: 'Quận Thủ Đức'},
    {
    id: 3,
    img: 'https://picsum.photos/id/13/600',
    name: 'Quận 1'},
    {
    id: 4,
    img: 'https://picsum.photos/id/14/600',
    name: 'Quận 2'},
    {
    id: 5,
    img: 'https://picsum.photos/id/15/600',
    name: 'Quận 3'},
    {
    id: 6,
    img: 'https://picsum.photos/id/16/600',
    name: 'Quận 4'},
    {
    id: 7,
    img: 'https://picsum.photos/id/17/600',
    name: 'Quận 6'},
    {
    id: 8,
    img: 'https://picsum.photos/id/18/600',
    name: 'Huyện Củ Chi'},
    {
    id: 9,
    img: 'https://picsum.photos/id/19/600',
    name: 'Huyện Nhà Bè'},
  ];
  constructor() { }

  ngOnInit() {
  }

}
