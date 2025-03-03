import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ForumComponent } from './forum/forum.component';
import { CreateTopicFormComponent } from './forum/create-topic-form/create-topic-form.component';

const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"forum",component:ForumComponent},
  {path:"forum/create",component:CreateTopicFormComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
