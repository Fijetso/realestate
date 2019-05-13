import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent, MatAutocompleteSelectedEvent, MatAutocomplete} from '@angular/material';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { ModalService } from 'src/app/services/modal.service';
export interface Fruit {
  name: string;
}

@Component({
  selector: 'app-realestate-list',
  templateUrl: './realestate-list.component.html',
  styleUrls: ['./realestate-list.component.scss']
})
export class RealestateListComponent implements OnInit {

  isLoading = true;
  constructor(private modalService: ModalService) {

  }
  openInfoModal() {
    this.modalService.openInfoModal();
  }
  ngOnInit() {
  }

  toogleSpinner() {
    this.isLoading = !this.isLoading;
    console.log(this.isLoading);
  }
}
