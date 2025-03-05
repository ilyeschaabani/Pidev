import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './projet/project-list/project-list.component';
import { ProjectDetailsComponent } from './projet/project-details/project-details.component'; 
import { ProjectValidationComponent } from './Admin/project-validation/project-validation.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AssignEncadrantComponent } from './Admin/assign-encadrant/assign-encadrant.component';
const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'projets', component: ProjectListComponent },
  { path: 'projet/:id', component: ProjectDetailsComponent },  // Route pour les détails du projet
  {path: 'navbar', component: NavbarComponent},
  {path: 'footer', component: FooterComponent},
  { path: 'assign-encadrant', component: AssignEncadrantComponent },
  {path: 'validationsProjet', component: ProjectValidationComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
