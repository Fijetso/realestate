import { FavoriteComponent } from './components/favorite/favorite.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RealestateListComponent } from './components/home/realestate-list.component';
import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MapModuleComponent } from './components/map-module/map-module.component';
import { RealEstateDetailComponent } from './core/ui/home-page/real-estate-detail/real-estate-detail.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  {
    path: 'list',
    component: RealestateListComponent,
    data: { title: 'List data page' }
  },
  {
    path: 'detail',
    component: RealEstateDetailComponent,
    data: { title: 'Real Estate Detail' }
  },
  { path: 'map', component: MapModuleComponent, data: { title: 'Map page' } },
  { path: 'login', component: LoginComponent, data: { title: 'Login page' } },
  {
    path: 'favorite',
    component: FavoriteComponent,
    data: { title: 'Favorite Page' }
  },
  {
    path: '**',
    component: PageNotFoundComponent,
    data: { title: 'Page not found' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [
  RealestateListComponent,
  RealestateItemComponent
];
