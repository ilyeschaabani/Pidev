import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminManageProjectComponent } from './admin-manage-project.component';

describe('AdminManageProjectComponent', () => {
  let component: AdminManageProjectComponent;
  let fixture: ComponentFixture<AdminManageProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminManageProjectComponent]
    });
    fixture = TestBed.createComponent(AdminManageProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
