import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';
import { RealestateListComponent } from './components/realestate-list/realestate-list.component';
import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { RealestateService } from './service/realestate/realestate.service';
import { MessageService } from './service/message/message.service'
@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    MessagesComponent,
    RealestateListComponent,
    RealestateItemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [MessageService, RealestateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
