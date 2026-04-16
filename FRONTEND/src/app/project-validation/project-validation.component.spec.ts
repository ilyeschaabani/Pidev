import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectValidationComponent } from './project-validation.component';

describe('ProjectValidationComponent', () => {
  let component: ProjectValidationComponent;
  let fixture: ComponentFixture<ProjectValidationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectValidationComponent]
    });
    fixture = TestBed.createComponent(ProjectValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
