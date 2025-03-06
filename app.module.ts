import { NgModule  } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { EvaluationComponent } from './QUIZ/evaluation/evaluation.component';
import { QuestionFormComponent } from './QUIZ/question-form/question-form.component';
import { EvaluationListComponent } from './QUIZ/evaluation-list/evaluation-list.component';
import { HeaderComponent } from './header/header.component';
import { ModifierQuizComponent } from './QUIZ/modifier-quiz/modifier-quiz.component';
import { UserDashboardComponent } from './QUIZ/user-dashboard/user-dashboard.component';
import { PasserevaluationComponent } from './QUIZ/passerevaluation/passerevaluation.component';
import { QuizComponent } from './QUIZ/quizapi/quiz.component';
import { CreateEvaluationComponent } from './QUIZ/create-evaluation/create-evaluation.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    EvaluationComponent,
    HomeComponent,
    QuestionFormComponent,
    EvaluationListComponent,
    HeaderComponent,
    ModifierQuizComponent,
    UserDashboardComponent,
    PasserevaluationComponent,
    QuizComponent,
    CreateEvaluationComponent,
   
 
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
