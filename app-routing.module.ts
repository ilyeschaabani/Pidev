import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { EvaluationComponent } from './QUIZ/evaluation/evaluation.component';
import { EvaluationListComponent } from './QUIZ/evaluation-list/evaluation-list.component';
import { ModifierQuizComponent } from './QUIZ/modifier-quiz/modifier-quiz.component';
import { UserDashboardComponent } from './QUIZ/user-dashboard/user-dashboard.component';
import { PasserevaluationComponent } from './QUIZ/passerevaluation/passerevaluation.component';
import { QuizComponent } from './QUIZ/quizapi/quiz.component';
import { CreateEvaluationComponent } from './QUIZ/create-evaluation/create-evaluation.component';


const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'evaluation', component: EvaluationComponent }, // Page de création
  { path: 'evaluation-list', component: EvaluationListComponent },
  { path: 'passer-evaluation/:id', component: PasserevaluationComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'create-evaluation', component: CreateEvaluationComponent }, // Route pour créer une évaluation
  { path: 'quiz', component: QuizComponent },
  {  path: 'modifier/:id', component: ModifierQuizComponent }, // Page de liste
  { path: '', redirectTo: '/create-evaluation', pathMatch: 'full' }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
