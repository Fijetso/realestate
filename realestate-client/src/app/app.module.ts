import { BrowserModule } from '@angular/platform-browser';
import { NgModule,NO_ERRORS_SCHEMA  } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule,routingComponents } from './app-routing.module';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';
import { RealestateListComponent } from './components/realestate-list/realestate-list.component';
import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { RealestateService } from './service/realestate/realestate.service';
import { MessageService } from './service/message/message.service';
import { MainNavComponent } from './components/main-nav/main-nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './components/carousel/carousel.component';
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [MessageService, RealestateService],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
