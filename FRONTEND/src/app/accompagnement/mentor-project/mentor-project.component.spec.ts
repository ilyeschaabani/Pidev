import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorProjectComponent } from './mentor-project.component';

describe('MentorProjectComponent', () => {
  let component: MentorProjectComponent;
  let fixture: ComponentFixture<MentorProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MentorProjectComponent]
    });
    fixture = TestBed.createComponent(MentorProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
