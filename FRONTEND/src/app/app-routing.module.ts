import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RessourceListComponent } from './ressource/ressource-list/ressource-list.component';
import { AddRessourceComponent } from './ressource/add-ressource/add-ressource.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'ressources', component:RessourceListComponent},
  {path:'addressources', component: AddRessourceComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
