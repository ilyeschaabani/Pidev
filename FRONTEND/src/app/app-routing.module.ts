import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { EmailInputComponent } from './email-input/email-input.component';
import { OTPverificationComponent } from './otpverification/otpverification.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { LoginSuccessComponent } from './login-success/login-success.component';  
import { ProfileComponent } from './profile/profile.component';
import { ShopFormationComponent } from './shop-formation/shop-formation.component'; 
import { FormationaddComponent } from './formation/formationadd/formationadd.component';
import { DashbordadminComponent } from './dashbordadmin/dashbordadmin.component';
import { PanierComponent } from './panier/panier.component';
import { FormationeditComponent } from './formation/formationedit/formationedit.component';
import { FormationlistComponent } from './formation/formationlist/formationlist.component';
import { FormationstatComponent } from './formation/formationstat/formationstat.component';
import { AddquizComponent } from './addquiz/addquiz.component';
import { AddquestionComponent } from './addquestion/addquestion.component';
import { DashbordevaluationComponent } from './dashbordevaluation/dashbordevaluation.component';
import { ModifiequestionComponent } from './modifiequestion/modifiequestion.component';
import { AssignQuestionsComponent } from './assign-questions/assign-questions.component';
import { EvaluationListComponent } from './evaluation-list/evaluation-list.component';
import { ViewQuizQuestionsComponent } from './view-quiz-questions/view-quiz-questions.component';
import { GamificationDashboardComponent } from './gamification-dashboard/gamification-dashboard.component';
import { QuizSessionComponent } from './quiz-session/quiz-session.component';
import { QuizAPIComponent } from './quiz-api/quiz-api.component';



const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'login', component: LoginComponent },
  {path:'signup',component:SignupComponent},
  {path:'email-input',component:EmailInputComponent},
  {path: 'otpverification',component:OTPverificationComponent },
  {path: 'reset',component:ResetPasswordComponent},
  {path:'login-success', component:LoginSuccessComponent  }, 
  {path: 'profile',component:ProfileComponent},
  {path: 'shop-formation', component: ShopFormationComponent}, 
   { path: 'formations/add',component:FormationaddComponent },
   { path: 'dashbord',component:DashbordadminComponent },
   { path: 'panier',component:PanierComponent },
   { path: 'formations/edit',component:FormationeditComponent },
   { path: 'formations/list',component:FormationlistComponent },
   { path: 'formations/stat',component:FormationstatComponent },
   { path: 'quiz/add',component:AddquizComponent },
   { path: 'question/add',component:AddquestionComponent},
   { path: 'evaluation/dashbord',component:DashbordevaluationComponent},
   { path: 'evaluation-list', component: EvaluationListComponent },
   {  path: 'view-questions/:id', component:ViewQuizQuestionsComponent }, 
   {  path: 'Gamification', component: GamificationDashboardComponent}, 
   {  path: 'quiz/:id', component: QuizSessionComponent},
   { path: 'quiz', component:QuizAPIComponent}, 
   {
    path: 'admin/evaluations/edit/:idEvaluation',
    component:ModifiequestionComponent,
    // Ajoutez ceci pour le débogage :
    canActivate: [() => {
      console.log('Route activée avec ID:', history.state);
      return true;
    }]
  },
  {  path: 'affecter', component:AssignQuestionsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
