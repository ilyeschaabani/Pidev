import { Component, OnInit } from '@angular/core';
import { Project } from '../../models/project.model';
import { PfeService } from '../../Services/pfe.service';

@Component({
  selector: 'app-students-project',
  templateUrl: './students-project.component.html',
  styleUrls: ['./students-project.component.css']
})
export class StudentsProjectComponent implements OnInit {
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
