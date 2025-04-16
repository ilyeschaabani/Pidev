import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationaddComponent } from './formationadd.component';

describe('FormationaddComponent', () => {
  let component: FormationaddComponent;
  let fixture: ComponentFixture<FormationaddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationaddComponent]
    });
    fixture = TestBed.createComponent(FormationaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
