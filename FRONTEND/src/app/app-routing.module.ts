import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './projet/project-list/project-list.component';
import { HomeComponent } from './home/home.component';
const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'projets', component: ProjectListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
