import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorListComponent } from './mentor-list.component';

describe('MentorListComponent', () => {
  let component: MentorListComponent;
  let fixture: ComponentFixture<MentorListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MentorListComponent]
    });
    fixture = TestBed.createComponent(MentorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
