import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsProjectComponent } from './students-project.component';

describe('StudentsProjectComponent', () => {
  let component: StudentsProjectComponent;
  let fixture: ComponentFixture<StudentsProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsProjectComponent]
    });
    fixture = TestBed.createComponent(StudentsProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
