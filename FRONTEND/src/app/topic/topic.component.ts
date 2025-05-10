import { Component, EventEmitter, Input, Output } from '@angular/core';
import { EventTopic } from '../Models/eventTopic.model';
import { EmojiType, Reaction,EmojiMap } from '../Models/reaction.model';
import { EventTopicService } from '../services/eventtopic/event-topic.service';
import { FileStorageService } from '../services/fileStorage/file-storage.service';
import { ReactionService } from '../services/reaction/reaction.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {
  constructor(private reactionService:ReactionService,private tp:EventTopicService,private fileStorageService:FileStorageService){

  }
  
  @Input() eventTopic!: EventTopic; // Input property to receive data
  @Output() notifyParent = new EventEmitter<void>();  // Output event to notify parent without data

  loading:boolean=false;
    userId="14526426456" //localStorage.getItem('loggedUserId');
   commentsIsOpen=false;
  emojiTypes: EmojiType[] = [EmojiType.LIKE, EmojiType.HAHA, EmojiType.LOVE, EmojiType.SAD, EmojiType.ANGRY];  // All possible reactions
  getMedia():String{
    if(this.eventTopic.media && this.eventTopic.mediaType)
    return this.fileStorageService.downloadFile(this.eventTopic.mediaType,this.eventTopic.media);
  return ""
  }
  
  getReactionCount(reactions: Reaction[] | undefined, emojiType: EmojiType): number {
    if (!reactions) {
      return 0;  // If no reactions are available, return 0
    }
    return reactions.filter(reaction => reaction.emoji === emojiType).length;
  }
   emojiMap=EmojiMap;
   addReaction(emojiType:EmojiType){
    this.loading=true;
    const entityName="eventTopic";
    const data={
      emoji:emojiType,
      userId:this.userId,
      entityName:entityName,
      entityId:this.eventTopic.id

    }
    this.reactionService.addReaction(data).subscribe(()=>{
      this.loading=false;
      this.notifyParent.emit();

    })
    }
    hasUserReacted(emojiType: string): boolean {
      return this.eventTopic.reactions.some(reaction => 
        reaction.userId === this.userId && reaction.emoji === emojiType);
    }
    openCommentSpace():void {
      this.commentsIsOpen=!this.commentsIsOpen;
    }
    changeReaction(emojiType:EmojiType){
      if(this.hasUserReacted(emojiType)){
        this.loading=true;
         const reactionIndex=this.eventTopic.reactions.findIndex((reaction)=>reaction.userId===this.userId && reaction.emoji===emojiType);
         const reaction=reactionIndex!=-1?this.eventTopic.reactions[reactionIndex]:null;
         if(reaction){
          this.reactionService.removeReaction(reaction).subscribe(()=>{
            this.loading=false;
            this.notifyParent.emit()})
         }
         }else{
        this.addReaction(emojiType)
      }
    }
    deletePost(){
      this.tp.delete(this.eventTopic.id||'').subscribe(()=>{
          this.notifyParent.emit();
      })
    }
    isOwnerOfPost(){
      return this.userId===this.eventTopic.userId;
    }
    isGoogleMapFormat(location: string): boolean {
      const regex = /Lat:\s*(-?\d+\.\d+),\s*Lng:\s*(-?\d+\.\d+)/;
      return regex.test(location);  // Test if location matches the Google Maps format
    }
    
    changeToGoogleMapLink(location: string): string {
      if (this.isGoogleMapFormat(location)) {
        // Use regex to extract latitude and longitude
        const regex = /Lat:\s*(-?\d+\.\d+),\s*Lng:\s*(-?\d+\.\d+)/;
        const match = location.match(regex);
    
        if (match) {
          const latitude = parseFloat(match[1]);
          const longitude = parseFloat(match[2]);
    
          // Return the Google Maps link with the extracted coordinates
          return `https://www.google.com/maps?q=${latitude},${longitude}`;
        }
      }
    
      // If the format doesn't match, return the original location string
      return location;
    }

}
