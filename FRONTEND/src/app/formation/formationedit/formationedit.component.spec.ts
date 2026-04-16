import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationeditComponent } from './formationedit.component';

describe('FormationeditComponent', () => {
  let component: FormationeditComponent;
  let fixture: ComponentFixture<FormationeditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationeditComponent]
    });
    fixture = TestBed.createComponent(FormationeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
