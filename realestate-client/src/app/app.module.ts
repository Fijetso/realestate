import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction'; // agm-direction
import { MatDialogModule } from '@angular/material/dialog';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';

import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { MapModuleComponent } from './components/map-module/map-module.component';

import {
  FormBuilder,
  FormGroup,
  Validators,
  FormsModule,
  NgForm
} from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import {
  MatButtonModule,
  MatCardModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatProgressSpinnerModule,
  MatTableModule,
  MatExpansionModule,
  MatSelectModule,
  MatSnackBarModule,
  MatTooltipModule,
  MatChipsModule,
  MatListModule,
  MatSidenavModule,
  MatTabsModule,
  MatProgressBarModule,
  MatRadioModule,
  MatSlideToggleModule,
  MatAutocompleteModule,
  MatBottomSheet,
  MatBottomSheetRef
} from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import 'hammerjs';
import { LazyLoadImageModule } from 'ng-lazyload-image';
import { NgxHmCarouselModule } from 'ngx-hm-carousel';


import { RealEstateWrapperComponent } from './components/real-estate/real-estate-wrapper/real-estate-wrapper.component';
import { AlertComponent } from './components/modal/alert/alert.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    MessagesComponent,
    routingComponents,
    FooterComponent,
    PageNotFoundComponent,
    ContentComponent,
    CarouselComponent,
    MapModuleComponent,
    LoginComponent,
    NavComponent,
    RealEstateWrapperComponent,
    AlertComponent,
    FavoriteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LazyLoadImageModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCxEsgD2edDcqz5wgKqjYJVjcqmWztNF3A'
    }),
    AgmDirectionModule,
    NgxHmCarouselModule,
    FormsModule,
    MatDialogModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    HttpClientModule,
    MatTooltipModule,
    MatInputModule,
    MatRadioModule,
    MatSlideToggleModule,
    MatAutocompleteModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
  entryComponents: [AlertComponent]
})
export class AppModule {}
