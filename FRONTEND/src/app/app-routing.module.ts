import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './projet/project-list/project-list.component';
import { ProjectDetailsComponent } from './projet/project-details/project-details.component'; 
import { ProjectValidationComponent } from './Admin/project-validation/project-validation.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AssignEncadrantComponent } from './Admin/assign-encadrant/assign-encadrant.component';
import { FileManagerComponent } from './files/file-manager/file-manager.component';
import { CollaborativeSpaceComponent } from './projet/collaborative-space/collaborative-space.component';
import { TaskListComponent } from './projet/task-list/task-list.component';
import { StudentsProjectComponent } from './accompagnemenet/components/students-project/students-project.component';
import { ProjetPfeListComponent } from './accompagnemenet/components/projet-pfe-list/projet-pfe-list.component';
import { PfeDetailsComponent } from './accompagnemenet/components/pfe-details/pfe-details.component';
const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'projets', component: ProjectListComponent },
  { path: 'projet/:id', component: ProjectDetailsComponent },  // Route pour les détails du projet
  {path: 'navbar', component: NavbarComponent},
  {path: 'footer', component: FooterComponent},
  { path: 'assign-encadrant', component: AssignEncadrantComponent },
  {path: 'validationsProjet', component: ProjectValidationComponent},
  { 
    path: 'collaborative-space/:id', 
    component: CollaborativeSpaceComponent 
  },
  { path: 'projects/:id/files', component: FileManagerComponent },

  { path: 'student', component: StudentsProjectComponent },
  { path: 'PfeList', component: ProjetPfeListComponent },
  { path: 'project-details/:id', component: PfeDetailsComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
