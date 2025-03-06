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
import { FormationStatsComponent } from './formation/formation-stats/formation-stats.component';
import { NgChartsModule } from 'ng2-charts';


@NgModule({
  declarations: [
    AppComponent,
    FormationListComponent,
    FormationAddComponent,
    NavbarComponent,
    FormationEditComponent,
    FormationStatsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
