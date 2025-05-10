import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationlistComponent } from './formationlist.component';

describe('FormationlistComponent', () => {
  let component: FormationlistComponent;
  let fixture: ComponentFixture<FormationlistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationlistComponent]
    });
    fixture = TestBed.createComponent(FormationlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
