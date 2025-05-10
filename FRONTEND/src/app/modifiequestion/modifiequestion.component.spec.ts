import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifiequestionComponent } from './modifiequestion.component';

describe('ModifiequestionComponent', () => {
  let component: ModifiequestionComponent;
  let fixture: ComponentFixture<ModifiequestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModifiequestionComponent]
    });
    fixture = TestBed.createComponent(ModifiequestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
