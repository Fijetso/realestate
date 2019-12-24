import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountRoutingModule } from './account-routing.module';
import { AccountInfoComponent } from './account-info/account-info.component';
import { PostGalleryComponent } from './post-gallery/post-gallery.component';
import { CollectionsComponent } from './collections/collections.component';


@NgModule({
  declarations: [AccountInfoComponent, PostGalleryComponent, CollectionsComponent],
  imports: [
    CommonModule,
    AccountRoutingModule
  ],
  exports :[
    AccountInfoComponent, PostGalleryComponent, CollectionsComponent
  ]
})
export class AccountModule { }
