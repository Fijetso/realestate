import { PopupService } from './services/map/popup.service';
import { MarkerService } from './services/map/marker.service';
import { MapComponent } from './core/ui/map/map.component';
import { HttpErrorInterceptor } from './services/common/http-error.interceptor';
import { environment } from './../environments/environment';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AgmCoreModule } from '@agm/core';
import { MatDialogModule } from '@angular/material/dialog';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './core/messages/messages.component';

import { FooterComponent } from './core/ui/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './core/ui/carousel/carousel.component';
import { MapModuleComponent } from './core/ui/map-module/map-module.component';
import { LoginComponent } from './core/ui/login/login.component';
import { NavComponent } from './core/ui/nav/nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import {
  MatButtonModule,
  MatCardModule,
  MatToolbarModule,
  MatIconModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatTooltipModule,
  MatListModule,
  MatSidenavModule,
  MatTabsModule,
  MatRadioModule,
  MatSlideToggleModule,
  MatAutocompleteModule,
  MatButtonToggleModule,
  MatFormFieldModule
} from '@angular/material';
import { HttpClientModule, HttpClient,HTTP_INTERCEPTORS } from '@angular/common/http';
import 'hammerjs';
import { LazyLoadImageModule } from 'ng-lazyload-image';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { OwlModule } from 'ngx-owl-carousel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularFireModule } from '@angular/fire';
import { AngularFireDatabaseModule } from '@angular/fire/database';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { ToastrModule } from 'ngx-toastr';
import {} from 'googlemaps';
import { ApolloModule } from 'apollo-angular';
import { HttpLinkModule } from 'apollo-angular-link-http';

import { RealEstateWrapperComponent } from './components/real-estate/real-estate-wrapper/real-estate-wrapper.component';
import { AlertComponent } from './core/modal/alert/alert.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { UploaderComponent } from './core/ui/uploader/uploader.component';
import { NewsItemComponent } from './core/ui/item-module/news-item/news-item.component';
import { FloatingMenuWrapperComponent } from './core/ui/menu/floating-menu-wrapper/floating-menu-wrapper.component';
import { FloatingMenuItemComponent } from './core/ui/menu/floating-menu-item/floating-menu-item.component';
import { MarkettingComponent } from './core/ui/home-page/marketting/marketting.component';
import { TradingAreaItemComponent } from './core/ui/trading-area/trading-area-item/trading-area-item.component';
import { WhyChooseSectionComponent } from './core/ui/home-page/why-choose-section/why-choose-section.component';
import { TradingAreaSectionComponent } from './core/ui/home-page/trading-area-section/trading-area-section.component';
import { HotRealEstateSectionComponent } from './core/ui/home-page/hot-real-estate-section/hot-real-estate-section.component';
import { RealEstateAppraisedSectionComponent } from './core/ui/home-page/real-estate-appraised-section/real-estate-appraised-section.component';
import { BuyOnDemandSectionComponent } from './core/ui/home-page/buy-on-demand-section/buy-on-demand-section.component';
import { NewsSectionComponent } from './core/ui/home-page/news-section/news-section.component';
import { DownloadAppSectionComponent } from './core/ui/home-page/download-app-section/download-app-section.component';
import { SectionHeaderComponent } from './core/ui/section-header/section-header.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { RealEstateDetailComponent } from './components/real-estate-detail/real-estate-detail.component';

export function LanguageLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
}
import { HotPlaceItemComponent } from './core/ui/item-module/hot-place-item/hot-place-item.component';
import { RegisterComponent } from './core/ui/register/register.component';
import { AuthenticationService } from './services/authentication/authentication.service';
import { UserComponent } from './components/user/user.component';
import { ApiService } from './services/api/api.service';
import { CreatePostComponent } from './core/ui/create-post/create-post.component';
import { FilterPipe } from './ultility/pipe/filter.pipe';
import { UserDetailComponent } from './core/ui/user-detail/user-detail.component';
import { GetCityPipe } from './ultility/pipe/get-city.pipe';
import { GetDistrictNameFromIdPipe } from './ultility/pipe/get-district-name-from-id.pipe';
import { ThousandSuffixPipe } from './ultility/pipe/thousand-suffix.pipe';
import { SearchPageComponent } from './core/ui/search-page/search-page.component';
import { AccountManagementComponent } from './core/ui/account-management/account-management.component';
import {HereMapsModule } from 'ng2-heremaps';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { PopupRendererComponent } from './core/ui/map/popup-renderer/popup-renderer.component';
import { AddReComponent } from './core/ui/create-post/add-re/add-re.component';
import { UpdateReComponent } from './core/ui/create-post/update-re/update-re.component';
import { DeleteReComponent } from './core/ui/create-post/delete-re/delete-re.component';

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
    FavoriteComponent,
    UploaderComponent,
    NewsItemComponent,
    FloatingMenuWrapperComponent,
    FloatingMenuItemComponent,
    MarkettingComponent,
    TradingAreaItemComponent,
    WhyChooseSectionComponent,
    TradingAreaSectionComponent,
    HotRealEstateSectionComponent,
    RealEstateAppraisedSectionComponent,
    BuyOnDemandSectionComponent,
    NewsSectionComponent,
    DownloadAppSectionComponent,
    SectionHeaderComponent,
    RealEstateDetailComponent,
    HotPlaceItemComponent,
    RegisterComponent,
    UserComponent,
    CreatePostComponent,
    FilterPipe,
    UserDetailComponent,
    GetCityPipe,
    GetDistrictNameFromIdPipe,
    ThousandSuffixPipe,
    SearchPageComponent,
    AccountManagementComponent,
    MapModuleComponent,
    MapComponent,
    PopupRendererComponent,
    AddReComponent,
    UpdateReComponent,
    DeleteReComponent
  ],
  imports: [
    OwlModule,
    BrowserModule,
    AppRoutingModule,
    LazyLoadImageModule,
    BrowserAnimationsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCTD49hV20zPTBjT7k643sBxjoXx6W7oEo',
      libraries: ['places']
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: LanguageLoader,
        deps: [HttpClient]
      }
    }),
    MatFormFieldModule,
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
    MatCardModule,
    MatButtonToggleModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    Ng2SearchPipeModule,
    ToastrModule.forRoot(),
    HereMapsModule.forRoot({
      apiKey: 'KLtdq3MAUJruxhiJ2GyAFQ',
      appId: 'dmdRFi5x5pT0zuy09gle',
      apiVersion: '3.0',
      libraries: ['core', 'service','ui','mapevents']
    }),
    LeafletModule.forRoot(),
    HttpClientModule,
    ApolloModule,
    HttpLinkModule
  ],
  providers: [
    AuthenticationService,
    ApiService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    },
    MarkerService,
    PopupService
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
  entryComponents: [AlertComponent]
})
export class AppModule {}
