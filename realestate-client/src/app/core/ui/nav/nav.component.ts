import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { Component, Input } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { ModalService } from 'src/app/services/modal.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {
  loginInfor = JSON.parse(localStorage.getItem('userInfor'));
  @Input() badgeCount = 5;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );
    isLoading = true;
  // constructor(private breakpointObserver: BreakpointObserver) {}
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer,
    private breakpointObserver: BreakpointObserver,
    private modalService: ModalService,
    private location: Location,
    private auth: AuthenticationService
  ) {
    this.matIconRegistry.addSvgIcon(
      'apple-badge',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/images/apple-badge.svg')
    );
    this.matIconRegistry.addSvgIcon(
      'google-badge',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/images/google-badge.svg')
    );
    // alert(this.loginInfor);
  }
  openInfoModal() {
    this.modalService.openInfoModal();
  }
  logOut() {
    this.auth.logOut();
    this.loginInfor = JSON.parse(localStorage.getItem('userInfor'));
  }
}
