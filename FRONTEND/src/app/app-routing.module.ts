import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RessourceListComponent } from './ressource/ressource-list/ressource-list.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'ressources', component:RessourceListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
