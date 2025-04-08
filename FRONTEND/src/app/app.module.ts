import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RessourceListComponent } from './ressource/ressource-list/ressource-list.component';
import { AddRessourceComponent } from './ressource/add-ressource/add-ressource.component';
import { NavbarComponent } from './navbar/navbar/navbar.component';
import { ResumeComponent } from './ressource/resume/resume.component';




@NgModule({
  declarations: [
    AppComponent,
    RessourceListComponent,
    AddRessourceComponent,
    NavbarComponent,
    ResumeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
