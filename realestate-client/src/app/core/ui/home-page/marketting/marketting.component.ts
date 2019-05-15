import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable, Subject, merge } from 'rxjs';
import {
  startWith,
  map,
  debounceTime,
  distinctUntilChanged,
  filter
} from 'rxjs/operators';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-marketting',
  templateUrl: './marketting.component.html',
  styleUrls: ['./marketting.component.scss']
})
export class MarkettingComponent implements OnInit {
  model: any;
  states = [
    'Quận 1',
    'Quận 2',
    'Quận 3',
    'Quận 4',
    'Quận 5',
    'Quận 6',
    'Quận 7',
    'Quận 8',
    'Quận 9',
    'Quận 10',
    'Quận 11',
    'Quận 12',
    'Quận Thủ Đức',
    'Quận Bình Thạnh',
    'Quận Gò Vấp',
    'Quận Phú Nhuận',
    'Quận Tân Phú',
    'Quận Bình Tân',
    'Quận Tân Bình',
    'Huyện Nhà Bè',
    'Huyện Bình Chánh',
    'Huyện Hóc Môn',
    'Huyện Củ Chi',
    'Huyện Cần Giờ'
  ];
  @ViewChild('instance') instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );
    const clicksWithClosedPopup$ = this.click$.pipe(
      filter(() => !this.instance.isPopupOpen())
    );
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term =>
        (term === ''
          ? this.states
          : this.states.filter(
              v => v.toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };
  selectedVal: string;
  ngOnInit() {
    this.selectedVal = 'buy';
  }

  public onValChange(val: string) {
    this.selectedVal = val;
  }
}
