import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MentorService } from '../../services/mentors/mentor.service';

@Component({
  selector: 'app-mentor-project',
  templateUrl: './mentor-project.component.html',
  styleUrls: ['./mentor-project.component.css']
})
export class MentorProjectComponent {
  projectId?: string; // Marquer comme nullable
  project: any = {};
  evaluation: any = { progressScore: 0, finalScore: 0, feedback: '' };
  comment: any = { content: '', createdBy: '' };

  constructor(
    private pfeProjectService: MentorService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    this.loadProject();
  }

  loadProject(): void {
    if (!this.projectId) {
      console.error("Project ID is not available");
      return;
    }
    this.pfeProjectService.getProjects().subscribe((projects: any) => {
      this.project = projects.find((p: any) => p.id === this.projectId);
    });
  }

  submitEvaluation(): void {
    if (!this.projectId) {
      console.error("Project ID is not available");
      return;
    }
    this.pfeProjectService.addEvaluation(this.projectId, this.evaluation).subscribe(
      response => {
        alert('Evaluation added successfully');
        this.loadProject();
      },
      error => {
        alert('Error adding evaluation');
      }
    );
  }

  submitComment(): void {
    if (!this.projectId) {
      console.error("Project ID is not available");
      return;
    }
    this.comment.createdBy = 'Mentor'; // assuming you set the current user's role
    this.pfeProjectService.addComment(this.projectId, this.comment).subscribe(
      response => {
        alert('Comment added successfully');
        this.loadProject();
      },
      error => {
        alert('Error adding comment');
      }
    );
  }

  goBack(): void {
    this.location.back();
  }

}
