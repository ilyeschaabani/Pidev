import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectPfeListComponent } from './project-pfe-list.component';

describe('ProjectPfeListComponent', () => {
  let component: ProjectPfeListComponent;
  let fixture: ComponentFixture<ProjectPfeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectPfeListComponent]
    });
    fixture = TestBed.createComponent(ProjectPfeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
