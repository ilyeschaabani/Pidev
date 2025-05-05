import { NgModule  } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
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
import { ColaborativespaceComponent } from './colaborativespace/colaborativespace.component';
import { FilemanagerComponent } from './files/filemanager/filemanager.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectlistComponent } from './projectlist/projectlist.component';
import { ProjectValidationComponent } from './project-validation/project-validation.component';
import { AssignEncadrantComponent } from './assign-encadrant/assign-encadrant.component';
import { AddressourceComponent } from './ressource/addressource/addressource.component';
import { PreviewComponent } from './ressource/preview/preview.component';
import { RessourcelistComponent } from './ressource/ressourcelist/ressourcelist.component';
import { SafeUrlPipe } from './safe-url.pipe';
import { ResumeComponent } from './ressource/resume/resume.component';
import { CommentComponent } from './comment/comment.component';
import { ReportformComponent } from './reportform/reportform.component';
import { CreatetopicformComponent } from './createtopicform/createtopicform.component';
import { ForumComponent } from './forum/forum.component';
import { TopicComponent } from './topic/topic.component';
import { AdminManageProjectComponent } from './accompagnement/admin-manage-project/admin-manage-project.component';
import { MentorListComponent } from './accompagnement/mentor-list/mentor-list.component';
import { MentorProjectComponent } from './accompagnement/mentor-project/mentor-project.component';
import { PfeDetailsComponent } from './accompagnement/pfe-details/pfe-details.component';
import { ProjectPfeListComponent } from './accompagnement/project-pfe-list/project-pfe-list.component';
import { StudentProjectComponent } from './accompagnement/student-project/student-project.component';
import { MatListModule } from '@angular/material/list';
import { CertifComponent } from './certif/certif.component';
import { PredictcoursComponent } from './predictcours/predictcours.component';
import { PredictionProjectComponent } from './prediction-project/prediction-project.component';
import { PredictionResultDialogComponent } from './prediction-result-dialog/prediction-result-dialog.component';
import { PredictionComponent } from './prediction/prediction.component';
import { PredictsourourComponent } from './predictsourour/predictsourour.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { PredictionFormTimespentComponent } from './prediction-form-timespent/prediction-form-timespent.component';
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
    ColaborativespaceComponent,
    FilemanagerComponent,
    ProjectDetailsComponent,
    ProjectlistComponent,
    ProjectValidationComponent,
    AssignEncadrantComponent,
    AddressourceComponent,
    PreviewComponent,
    RessourcelistComponent,
    SafeUrlPipe,
    ResumeComponent,
    CommentComponent,
    ReportformComponent,
    CreatetopicformComponent,
    ForumComponent,
    TopicComponent,
    AdminManageProjectComponent,
    MentorListComponent,
    MentorProjectComponent,
    PfeDetailsComponent,
    ProjectPfeListComponent,
    StudentProjectComponent,
    CertifComponent,
    PredictcoursComponent,
    PredictionProjectComponent,
    PredictionResultDialogComponent,
    PredictionComponent,
    PredictsourourComponent,
RecommendationComponent,
PredictionFormTimespentComponent
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
    MatListModule,
    MatDialogModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
