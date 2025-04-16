import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';  // Importer HttpClientModule
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectListComponent } from './projet/project-list/project-list.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { ProjectDetailsComponent } from './projet/project-details/project-details.component';
import { ProjectValidationComponent } from './Admin/project-validation/project-validation.component';
import { AssignEncadrantComponent } from './Admin/assign-encadrant/assign-encadrant.component';
import { FileManagerComponent } from './files/file-manager/file-manager.component';
import { CollaborativeSpaceComponent } from './projet/collaborative-space/collaborative-space.component';
import { TaskListComponent } from './projet/task-list/task-list.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { NgChartsModule } from 'ng2-charts';
import { ReactiveFormsModule } from '@angular/forms';
import { StudentsProjectComponent } from './accompagnemenet/components/students-project/students-project.component';
import { ProjetPfeListComponent } from './accompagnemenet/components/projet-pfe-list/projet-pfe-list.component';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PfeDetailsComponent } from './accompagnemenet/components/pfe-details/pfe-details.component';

@NgModule({
  declarations: [
    
    AppComponent,
    ProjectListComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    ProjectDetailsComponent,
    ProjectValidationComponent,
    AssignEncadrantComponent,
    FileManagerComponent,
    CollaborativeSpaceComponent,
    TaskListComponent,
    StudentsProjectComponent,
    ProjetPfeListComponent,
    PfeDetailsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DragDropModule,
    NgChartsModule,
    ReactiveFormsModule,
     MatIconModule,
    MatCardModule,
    MatDividerModule,
    MatExpansionModule,
    MatListModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
