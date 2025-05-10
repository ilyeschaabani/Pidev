import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationstatComponent } from './formationstat.component';

describe('FormationstatComponent', () => {
  let component: FormationstatComponent;
  let fixture: ComponentFixture<FormationstatComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationstatComponent]
    });
    fixture = TestBed.createComponent(FormationstatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
