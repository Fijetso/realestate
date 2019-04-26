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
// import { RealestateListComponent } from './components/realestate-list/realestate-list.component';
// import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { RealestateService } from './service/realestate/realestate.service';
import { MessageService } from './service/message/message.service';
import { MainNavComponent } from './components/main-nav/main-nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { MapModuleComponent } from './components/map-module/map-module.component';

import{ FormsModule} from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HereMapComponent } from './components/here-map/here-map.component'
@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    MessagesComponent,
    // RealestateListComponent,
    // RealestateItemComponent,
    routingComponents,
    MainNavComponent,
    FooterComponent,
    PageNotFoundComponent,
    ContentComponent,
    CarouselComponent,
    MapModuleComponent,
    LoginComponent,
    HereMapComponent,
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
    MatDialogModule
  ],
  providers: [MessageService, RealestateService],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
