import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ForumComponent } from './forum/forum.component';
import { TopicComponent } from './forum/topic/topic.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateTopicFormComponent } from './forum/create-topic-form/create-topic-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommentComponent } from './forum/comment/comment.component';
import { CommentFormComponent } from './forum/comment-form/comment-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ForumComponent,
    TopicComponent,
    CreateTopicFormComponent,
    CommentComponent,
    CommentFormComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,// <-- Make sure ReactiveFormsModule is imported here
    FormsModule,  // <-- Add FormsModule here

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
