import { Component } from '@angular/core';
import { PfeProjectService } from '../../services/pfe-project/pfe-project.service';
import { PFEProject } from '../../Models/pfe-project.model';
import * as bootstrap from 'bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mentor-list',
  templateUrl: './mentor-list.component.html',
  styleUrls: ['./mentor-list.component.css']
})
export class MentorListComponent {
  projets: PFEProject[] = [];
  selectedProject: PFEProject | null = null;

  newProject: PFEProject = {
    id: '',
    title: '',
    description: '',
    studentIds: [],
    mentorId: '',
    stage: 'PROPOSAL',
    documents: [],
    comments: [],
    evaluation: null
  };

  keywords: string = '';
  generatedTopics: string[] = [];

  constructor(private projectService: PfeProjectService,private router: Router) {}

  ngOnInit(): void {
    this.fetchProjects();
  }
  goToMentorProject(projectId: string): void {
    this.router.navigate(['/mentor/project', projectId]);
  }
  


  fetchProjects(): void {
    this.projectService.getProjects().subscribe((projects) => {
      this.projets = projects;
    });
  }


}
