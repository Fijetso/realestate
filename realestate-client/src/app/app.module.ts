import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction'; // agm-direction
import { MatDialogModule } from '@angular/material/dialog';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MessagesComponent } from './components/messages/messages.component';

import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ContentComponent } from './components/content/content.component';
import { CarouselComponent } from './core/ui/carousel/carousel.component';
import { MapModuleComponent } from './components/map-module/map-module.component';
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
  MatBottomSheetRef,
  MatButtonToggleModule,
  MatFormFieldModule
} from '@angular/material';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule, HttpClient } from '@angular/common/http';
import 'hammerjs';
import { LazyLoadImageModule } from 'ng-lazyload-image';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
// import { TranslateConfigService } from '../app/services/translate/translate-config.service';

import { RealEstateWrapperComponent } from './components/real-estate/real-estate-wrapper/real-estate-wrapper.component';
import { AlertComponent } from './components/modal/alert/alert.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { UploaderComponent } from './components/uploader/uploader.component';
import { NewsItemComponent } from './core/ui/news-item/news-item.component';
import { FloatingMenuWrapperComponent } from './core/ui/menu/floating-menu-wrapper/floating-menu-wrapper.component';
import { FloatingMenuItemComponent } from './core/ui/menu/floating-menu-item/floating-menu-item.component';
import { MarkettingComponent } from './core/ui/home-page/marketting/marketting.component';
import { TradingAreaItemComponent } from './core/ui/trading-area/trading-area-item/trading-area-item.component';
import { WhyChooseSectionComponent } from './core/ui/home-page/why-choose-section/why-choose-section.component';
import { TradingAreaSectionComponent } from './core/ui/home-page/trading-area-section/trading-area-section.component';
import { HotRealEstateSectionComponent } from './core/ui/home-page/hot-real-estate-section/hot-real-estate-section.component';
// tslint:disable-next-line: max-line-length
import { RealEstateAppraisedSectionComponent } from './core/ui/home-page/real-estate-appraised-section/real-estate-appraised-section.component';
import { BuyOnDemandSectionComponent } from './core/ui/home-page/buy-on-demand-section/buy-on-demand-section.component';
import { NewsSectionComponent } from './core/ui/home-page/news-section/news-section.component';
import { DownloadAppSectionComponent } from './core/ui/home-page/download-app-section/download-app-section.component';
import { SectionHeaderComponent } from './core/ui/section-header/section-header.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { RealEstateDetailComponent } from './core/ui/home-page/real-estate-detail/real-estate-detail.component';

export function LanguageLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
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
    RealEstateDetailComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    LazyLoadImageModule,
    SlickCarouselModule,
    BrowserAnimationsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCxEsgD2edDcqz5wgKqjYJVjcqmWztNF3A'
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: LanguageLoader,
        deps: [HttpClient]
      }
    }),
    AgmDirectionModule,
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
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
  entryComponents: [AlertComponent]
})
export class AppModule {}
