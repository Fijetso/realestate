import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RealestateListComponent } from './components/realestate-list/realestate-list.component';
import { RealestateItemComponent } from './components/realestate-item/realestate-item.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  {path:'list',component: RealestateListComponent},
  {path:'detail',component: RealestateItemComponent},
  { path: '**', component: PageNotFoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [RealestateListComponent,RealestateItemComponent];