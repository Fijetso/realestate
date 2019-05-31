import { CommonService } from './../../services/common/common.service';
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
  constructor(private modalService: ModalService, private common: CommonService) {

  }
  inputSlug = '';
  outPutSlug ='';
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
  convertToSlug(title: any) {
    this.outPutSlug = this.common.changeToSlug(title);
    console.log(this.inputSlug, this.outPutSlug);
  }
}
