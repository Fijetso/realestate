import { CommonService } from './../../services/common/common.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ModalService } from 'src/app/services/modal.service';
import { MarkettingComponent } from 'src/app/core/ui/home-page/marketting/marketting.component';
import { DataService } from './../../services/data/data.service';
@Component({
  selector: 'app-realestate-list',
  templateUrl: './realestate-list.component.html',
  styleUrls: ['./realestate-list.component.scss']
})
export class RealestateListComponent implements OnInit, AfterViewInit {

  outPutSlug: string;
  inputSlug: any;
  tradeKindSelected: any;
  constructor(private modalService: ModalService, private common: CommonService, private data: DataService) {
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
    this.data.currentTradeKindSelected.subscribe(tradeKind => {
      this.tradeKindSelected = tradeKind;
    });
  }
  ngAfterViewInit(): void {
    // console.log(this.marketting.reKindValue);
  }
  receiveREKind($event) {
    this.reKind = $event;
    console.log(this.reKind);
  }
}
