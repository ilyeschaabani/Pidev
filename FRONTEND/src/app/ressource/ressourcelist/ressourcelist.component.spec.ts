import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RessourcelistComponent } from './ressourcelist.component';

describe('RessourcelistComponent', () => {
  let component: RessourcelistComponent;
  let fixture: ComponentFixture<RessourcelistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RessourcelistComponent]
    });
    fixture = TestBed.createComponent(RessourcelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
