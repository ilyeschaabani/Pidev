import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColaborativespaceComponent } from './colaborativespace.component';

describe('ColaborativespaceComponent', () => {
  let component: ColaborativespaceComponent;
  let fixture: ComponentFixture<ColaborativespaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ColaborativespaceComponent]
    });
    fixture = TestBed.createComponent(ColaborativespaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
