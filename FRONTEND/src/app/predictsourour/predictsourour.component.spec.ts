import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictsourourComponent } from './predictsourour.component';

describe('PredictsourourComponent', () => {
  let component: PredictsourourComponent;
  let fixture: ComponentFixture<PredictsourourComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredictsourourComponent]
    });
    fixture = TestBed.createComponent(PredictsourourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
