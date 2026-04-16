import { Component } from '@angular/core';
import { Project } from '../../Models/projects.model';
import { PfeService } from '../../services/pfe/pfe.service';

@Component({
  selector: 'app-student-project',
  templateUrl: './student-project.component.html',
  styleUrls: ['./student-project.component.css']
})
export class StudentProjectComponent {
  projects: Project[] = [];
  selectedFile: File | null = null;
  generatedTopics: string = '';
  keywords: string = '';

  constructor(private projectService: PfeService) {}

  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getStudentProjects().subscribe((data) => {
      this.projects = data;
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  upload(projectId: string) {
    if (this.selectedFile) {
      this.projectService.uploadDocument(projectId, this.selectedFile).subscribe(() => {
        alert('Document uploaded');
        this.loadProjects();
      });
    }
  }

  generateTopicsFromKeywords() {
    this.projectService.generateTopics({ keywords: this.keywords }).subscribe((topics) => {
      this.generatedTopics = topics;
    });
  }

}
