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

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
