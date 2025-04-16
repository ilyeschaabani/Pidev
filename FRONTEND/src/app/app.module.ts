import { NgModule  } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { EmailInputComponent } from './email-input/email-input.component';
import { OTPverificationComponent } from './otpverification/otpverification.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { ProfileComponent } from './profile/profile.component';
import { ShopFormationComponent } from './shop-formation/shop-formation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card';
import { FormationlistComponent } from './formation/formationlist/formationlist.component';
import { FormationaddComponent } from './formation/formationadd/formationadd.component';
import { FormationeditComponent } from './formation/formationedit/formationedit.component';
import { FormationstatComponent } from './formation/formationstat/formationstat.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    NavbarComponent,
    ForgetPasswordComponent,
    EmailInputComponent,
    OTPverificationComponent,
    ResetPasswordComponent,
    LoginSuccessComponent,
    ProfileComponent,
    ShopFormationComponent,
    FormationlistComponent,
    FormationaddComponent,
    FormationeditComponent,
    FormationstatComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatCardModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
