import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignEncadrantComponent } from './assign-encadrant.component';

describe('AssignEncadrantComponent', () => {
  let component: AssignEncadrantComponent;
  let fixture: ComponentFixture<AssignEncadrantComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignEncadrantComponent]
    });
    fixture = TestBed.createComponent(AssignEncadrantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
