import { BrowserModule } from '@angular/platform-browser';
import { NgModule,NO_ERRORS_SCHEMA  } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule,routingComponents } from './app-routing.module';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';   // agm-direction
import {MatDialogModule} from '@angular/material/dialog';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';
import { RealestateService } from './service/realestate/realestate.service';
import { MessageService } from './service/message/message.service';

import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { MapModuleComponent } from './components/map-module/map-module.component';

import{ FormsModule} from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import {  MatButtonModule, MatCardModule, MatMenuModule, MatToolbarModule, MatIconModule, MatInputModule, MatDatepickerModule, MatNativeDateModule, MatProgressSpinnerModule,
  MatTableModule, MatExpansionModule, MatSelectModule, MatSnackBarModule, MatTooltipModule, MatChipsModule, MatListModule, MatSidenavModule, MatTabsModule, MatProgressBarModule } from '@angular/material';
import { NavigationBarComponent } from './core/components/navigation-bar/navigation-bar.component';
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
    NavigationBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCxEsgD2edDcqz5wgKqjYJVjcqmWztNF3A'
    }),
    AgmDirectionModule,
    FormsModule,
    MatDialogModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [MessageService, RealestateService],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
