import { Component, OnInit } from '@angular/core';
import { ProjectFile } from '../../models/project-file.model';
import { ActivatedRoute } from '@angular/router';
import { FileService } from '../file-service.service';
@Component({
  selector: 'app-file-manager',
  templateUrl: './file-manager.component.html',
  styleUrls: ['./file-manager.component.css']
})
export class FileManagerComponent implements OnInit {
  projectId!: string;
  files: ProjectFile[] = [];
  selectedFile: File | null = null;

  constructor(
    private fileService: FileService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    this.loadFiles();
  }
  loadFiles() {
    this.fileService.getProjectFiles(this.projectId).subscribe({
      next: (files) => {
        this.files = files;
      },
      error: (err) => {
        console.error('Failed to load files:', err);
      }
    });
  }

  // Rest of the component remains the same
  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.fileService.uploadFile(this.projectId, formData).subscribe({
        next: () => {
          this.loadFiles();
          this.selectedFile = null;
        },
        error: (err) => console.error('Upload failed:', err)
      });
    }
  }


  downloadFile(fileId: string, fileName: string): void {
    this.fileService.downloadFile(fileId).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = fileName;
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }
}