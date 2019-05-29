import { Component, OnInit} from '@angular/core';
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
  constructor(private modalService: ModalService) {

  }

  isLoading = true;
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
