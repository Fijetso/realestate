import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {
  isShow = true;
  constructor(
    public dialogRef: MatDialogRef<AlertComponent>
  ) { }
  ngOnInit() {
  }
  handleClick(){
    this.isShow = !this.isShow;
    console.log(this.isShow);
  }
  handleClose($event){
    this.dialogRef.close();
  }
}
