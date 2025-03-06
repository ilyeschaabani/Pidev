import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormationListComponent } from './formation/formation-list/formation-list.component';
import { HomeComponent } from './home/home.component';
import { FormationAddComponent } from './formation/formation-add/formation-add.component';
import { FormationEditComponent } from './formation/formation-edit/formation-edit.component';
import { FormationStatsComponent } from './formation/formation-stats/formation-stats.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'formations', component: FormationListComponent},
  { path: 'formations/add', component: FormationAddComponent},
  { path: 'formations/edit/:id', component: FormationEditComponent},
  { path: 'formations/stats', component: FormationStatsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
