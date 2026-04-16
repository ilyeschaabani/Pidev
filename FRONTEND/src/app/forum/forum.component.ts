import { Component } from '@angular/core';
import { EventTopic } from '../Models/eventTopic.model';
import { EventTopicService } from '../services/eventtopic/event-topic.service';
import { Router } from '@angular/router';  // Import Router

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  eventTopics: EventTopic[] = [];


  constructor(private eventTopicService: EventTopicService,private router:Router) {}

  ngOnInit(): void {
    this.loadEventTopics();
  }

  loadEventTopics(): void {
    this.eventTopicService.getAll().subscribe((data) => (this.eventTopics = data));
  } 
  createEventTopic(): void {
    this.router.navigate(['/createtopic']);  // Redirect to /forum/create
  }
  onGetNotification():void{
    this.loadEventTopics();

  }

}
