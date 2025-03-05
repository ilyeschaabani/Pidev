import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormationListComponent } from './formation/formation-list/formation-list.component';
import { FormationAddComponent } from './formation/formation-add/formation-add.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { FormationEditComponent } from './formation/formation-edit/formation-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    FormationListComponent,
    FormationAddComponent,
    NavbarComponent,
    FormationEditComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
