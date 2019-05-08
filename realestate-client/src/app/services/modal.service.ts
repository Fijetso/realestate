import { AlertComponent } from "./../components/modal/alert/alert.component";
import { Injectable } from "@angular/core";
import { MatDialog } from "@angular/material";

@Injectable({
  providedIn: "root"
})
export class ModalService {
  constructor(public dialog: MatDialog) {}
  openInfoModal() {
    const dialogRef = this.dialog.open(AlertComponent, {
      data: ""
    });
  }
  openWarningModal() {}
  openErrorModal() {}
  openConfirmModal() {}
}
