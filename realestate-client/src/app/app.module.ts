import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';
import { RealestateListComponent } from './components/realestate-list/realestate-list.component';
import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { RealestateService } from './service/realestate/realestate.service';
import { MessageService } from './service/message/message.service';
import { MainNavComponent } from './components/main-nav/main-nav.component';
import { FooterComponent } from './components/footer/footer.component'
@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    MessagesComponent,
    RealestateListComponent,
    RealestateItemComponent,
    MainNavComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [MessageService, RealestateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
