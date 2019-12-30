import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountRoutingModule } from './account-routing.module';
import { AccountInfoComponent } from './account-info/account-info.component';
import { PostGalleryComponent } from './post-gallery/post-gallery.component';
import { CollectionsComponent } from './collections/collections.component';
import { MatFormFieldModule, MatDatepickerModule, MatInputModule, MatSelectModule, MatButtonModule } from '@angular/material';


@NgModule({
  declarations: [AccountInfoComponent, PostGalleryComponent, CollectionsComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports : [
    AccountInfoComponent, PostGalleryComponent, CollectionsComponent
  ]
})
export class AccountModule { }
