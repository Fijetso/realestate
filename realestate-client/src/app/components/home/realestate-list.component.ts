import { CommonService } from './../../services/common/common.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ModalService } from 'src/app/services/modal.service';
import { MarkettingComponent } from 'src/app/core/ui/home-page/marketting/marketting.component';
@Component({
  selector: 'app-realestate-list',
  templateUrl: './realestate-list.component.html',
  styleUrls: ['./realestate-list.component.scss']
})
export class RealestateListComponent implements OnInit, AfterViewInit {

  outPutSlug: string;
  inputSlug: any;
  constructor(private modalService: ModalService, private common: CommonService) {

  }
  isLoading = true;
  reKind = '';
  @ViewChild(MarkettingComponent,{static: false}) marketting: MarkettingComponent;
  openInfoModal() {
    this.modalService.openInfoModal();
  }
  toogleSpinner() {
    this.isLoading = !this.isLoading;
    console.log(this.isLoading);
  }
  convertToSlug(title: any) {
    this.outPutSlug = this.common.changeToSlug(title);
    console.log(this.inputSlug, this.outPutSlug);
  }
  ngOnInit() {
  }
  ngAfterViewInit(): void {
    // console.log(this.marketting.reKindValue);
  }
  receiveREKind($event) {
    this.reKind = $event;
    console.log(this.reKind);
  }
}
