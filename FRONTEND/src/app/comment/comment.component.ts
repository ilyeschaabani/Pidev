import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../Models/comment.model';
import { CommentService } from '../services/comment/comment.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent  implements OnInit{
  comments: Comment[] = [];
  @Input() topicId!: string;
  @Input()userId!:string;
  loading=true; // Input property to receive data
  newCommentContent: string = '';  // Content of the new comment
  selectedComment:Comment|null=null;
  commentToReport:Comment|null=null;
constructor(private commentService: CommentService) {}

ngOnInit(): void {
  this.loadComments();
 }

loadComments(): void {
  this.commentService.getCommentByTopic(this.topicId).subscribe((data) => {this.comments = data; this.loading=false});
} 
addComment():void{
  if (this.newCommentContent.trim()) {
    const comment = new Comment({
      content: this.newCommentContent,
      eventTopicId: this.topicId,
      userId:this.userId
    });

    this.commentService.createComment(comment).subscribe(
      () => {
     this.loadComments();
     this.newCommentContent=""
      },
      () => {
        console.error("Error adding comment");
      }
    );
}

}
isOwnerOfComment(comment:Comment):boolean{
return this.userId===comment.userId
}
changeSelectedComment(selectedComment:Comment):void{
this.selectedComment=selectedComment;
}
updateComment():void{
if(this.selectedComment)
this.commentService.updateComment(this.selectedComment.id||"",this.selectedComment).subscribe(()=>{
this.loadComments();
  this.selectedComment=null;
})
}
deleteComment(commentId:string){
this.commentService.delete(commentId).subscribe(()=>{
  this.loadComments();
})
}
report(comment:Comment){
this.commentToReport=comment
}
closeReport(){
this.commentToReport=null;
}

}
