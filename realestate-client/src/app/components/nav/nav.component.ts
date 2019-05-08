import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { ModalService } from 'src/app/services/modal.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );
    isLoading: boolean = true;
  // constructor(private breakpointObserver: BreakpointObserver) {}
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer,
    private breakpointObserver: BreakpointObserver,
    private modalService: ModalService
  ){
    this.matIconRegistry.addSvgIcon(
      "apple-badge",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/images/apple-badge.svg")
    );
    this.matIconRegistry.addSvgIcon(
      "google-badge",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/images/google-badge.svg")
    );
  }
  openInfoModal() {
    this.modalService.openInfoModal();
  }
}
