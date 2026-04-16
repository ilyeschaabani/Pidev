import { Reaction } from "./reaction.model";

export class EventTopic {
    id?: string;
    title: string;
    description: string;
    createdAt: Date;
    eventDate?: Date; // Optionnel si c'est un topic
    event: boolean;
    location?: string; // Optionnel si c'est un topic
    commentIds: string[];
    reactions: Reaction[];
    reportIds: string[];
    userId:string;
    media?:string;
    mediaType?:"IMAGE"|"VIDEO";
    constructor(data?: Partial<EventTopic>) {
      this.id = data?.id;
      this.title = data?.title || '';
      this.description = data?.description || '';
      this.createdAt = data?.createdAt ? new Date(data.createdAt) : new Date();
      this.eventDate = data?.eventDate ? new Date(data.eventDate) : undefined;
      this.event = data?.event ?? false;
      this.location = data?.location;
      this.commentIds = data?.commentIds || [];
      this.reactions = data?.reactions || [];
      this.reportIds = data?.reportIds || [];
      this.userId=data?.userId||'';
      this.media=data?.media;
      this.mediaType=data?.mediaType;
    }
  }