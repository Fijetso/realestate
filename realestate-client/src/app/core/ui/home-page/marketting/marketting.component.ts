import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl  } from '@angular/forms';
import { Observable, Subject, merge } from 'rxjs';
import {
  startWith,
  map,
  debounceTime,
  distinctUntilChanged,
  filter
} from 'rxjs/operators';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';

export interface State {
  name: string;
}
@Component({
  selector: 'app-marketting',
  templateUrl: './marketting.component.html',
  styleUrls: ['./marketting.component.scss']
})
export class MarkettingComponent implements OnInit {
  selectedVal: string;

  myControl = new FormControl();
  states: State[] = [
    {name: 'Quận 1'},
    {name: 'Quận 2'},
    {name: 'Quận 3'},
    {name: 'Quận 4'},
    {name: 'Quận 5'},
    {name: 'Quận 6'},
    {name: 'Quận 7'},
    {name: 'Quận 8'},
    {name: 'Quận 9'},
    {name: 'Quận 10'},
    {name: 'Quận 11'},
    {name: 'Quận 12'},
    {name: 'Quận Bình Thạnh'},
    {name: 'Quận Gò Vấp'},
    {name: 'Quận Tân Bình'},
    {name: 'Quận Tân Phú'},
    {name: 'Quận Bình Tân'},
    {name: 'Huyện Nhà Bè'},
    {name: 'Huyện Bình Chánh'},
    {name: 'Huyện Hóc Môn'},
    {name: 'Huyện Củ Chi'},
    {name: 'Huyện Cần Giờ'},
  ];
  filteredOptions: Observable<State[]>;

  ngOnInit() {
    this.selectedVal = 'buy';
    this.filteredOptions = this.myControl.valueChanges
    .pipe(
      startWith<string | State>(''),
      map(value => typeof value === 'string' ? value : value.name),
      map(name => name ? this._filter(name) : this.states.slice())
    );
  }

  public onValChange(val: string) {
    this.selectedVal = val;
  }
  displayFn(state?: State): string | undefined {
    return state ? state.name : undefined;
  }

  private _filter(name: string): State[] {
    const filterValue = name.toLowerCase();

    return this.states.filter(option => option.name.toLowerCase().indexOf(filterValue) === 0);
  }
}
