import { AccountModule } from './core/ui/account/account.module';
import { environment } from './../environments/environment';
import { GraphQueryService } from './services/graphql/graph-query.service';
import { PopupService } from './services/map/popup.service';
import { MarkerService } from './services/map/marker.service';
import { MapComponent } from './core/ui/map/map.component';
import { HttpErrorInterceptor } from './services/common/http-error.interceptor';
import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule, routingComponents } from './app-routing.module';
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
  MatFormFieldModule,
  MatChipsModule,
  MatExpansionModule,
  MatSliderModule,
  MatCheckboxModule,
  MatProgressBarModule
} from '@angular/material';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { ApolloModule } from 'apollo-angular';
import { HttpLinkModule } from 'apollo-angular-link-http';
import { SocialLoginModule, AuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {CKEditorModule} from 'ngx-ckeditor';
import { NgxMapboxGLModule } from 'ngx-mapbox-gl';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

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
import { AddReComponent } from './core/ui/create-post/add-re/add-re.component';
import { UpdateReComponent } from './core/ui/create-post/update-re/update-re.component';
import { DeleteReComponent } from './core/ui/create-post/delete-re/delete-re.component';
import { ContactComponent } from './core/ui/contact/contact.component';
import { CookieService } from 'ngx-cookie-service';
import { NewsModuleModule } from './core/ui/news-module/news-module.module';
import { SearchPipe } from './ultility/pipe/search.pipe';
import { SortPipe } from './ultility/pipe/sort.pipe';
import { DisplayMapComponent } from './core/ui/map-box/display-map/display-map.component';
import { FengShuiComponent } from './core/ui/feng-shui/feng-shui.component';
import { RecommendTradeComponent } from './core/ui/recommend-trade/recommend-trade.component';
import { OndemandPipe } from './ultility/pipe/ondemand/ondemand.pipe';

const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider(environment.soicialProvider.google.clientId)
  },
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider(environment.soicialProvider.facebook.appId)
  }
]);

export function provideConfig() {
  return config;
}

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
    MapModuleComponent,
    MapComponent,
    AddReComponent,
    UpdateReComponent,
    DeleteReComponent,
    ContactComponent,
    SearchPipe,
    SortPipe,
    DisplayMapComponent,
    FengShuiComponent,
    RecommendTradeComponent,
    OndemandPipe
  ],
  imports: [
    OwlModule,
    BrowserModule,
    AppRoutingModule,
    LazyLoadImageModule,
    BrowserAnimationsModule,
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
    HttpClientModule,
    ApolloModule,
    HttpLinkModule,
    MatChipsModule,
    SocialLoginModule,
    AccountModule,
    NgbModule,
    NewsModuleModule,
    CKEditorModule,
    NgxMapboxGLModule.withConfig({
      accessToken: environment.mapbox.accessToken,
      geocoderAccessToken: environment.mapbox.geocoderAccessToken
    }),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    MatExpansionModule,
    MatSliderModule,
    MatCheckboxModule,
    MatProgressBarModule,
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
    PopupService,
    GraphQueryService,
    Title,
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    },
    CookieService
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
  entryComponents: [AlertComponent]
})
export class AppModule { }
