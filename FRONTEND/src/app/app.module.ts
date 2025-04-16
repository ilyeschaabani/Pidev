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
import { DashbordadminComponent } from './dashbordadmin/dashbordadmin.component';
import { PanierComponent } from './panier/panier.component';
import { NgChartsModule } from 'ng2-charts';
import { AddquizComponent } from './addquiz/addquiz.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { AddquestionComponent } from './addquestion/addquestion.component';
import { DashbordevaluationComponent } from './dashbordevaluation/dashbordevaluation.component';
import { ModifiequestionComponent } from './modifiequestion/modifiequestion.component';
import { AssignQuestionsComponent } from './assign-questions/assign-questions.component';
import { ToastrModule } from 'ngx-toastr';
import { EvaluationListComponent } from './evaluation-list/evaluation-list.component';
import { ViewQuizQuestionsComponent } from './view-quiz-questions/view-quiz-questions.component';
import { GamificationDashboardComponent } from './gamification-dashboard/gamification-dashboard.component';
import { QuizSessionComponent } from './quiz-session/quiz-session.component';
import { QuizAPIComponent } from './quiz-api/quiz-api.component';

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
    DashbordadminComponent,
    PanierComponent,
    AddquizComponent,
    AddquestionComponent,
    DashbordevaluationComponent,
    ModifiequestionComponent,
    AssignQuestionsComponent,
    EvaluationListComponent,
    ViewQuizQuestionsComponent,
    GamificationDashboardComponent,
    QuizSessionComponent,
    QuizAPIComponent,
    

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
    NgChartsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatIconModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatOptionModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatIconModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
