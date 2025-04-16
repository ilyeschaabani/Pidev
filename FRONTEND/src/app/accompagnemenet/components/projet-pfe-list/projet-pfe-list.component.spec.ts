import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetPfeListComponent } from './projet-pfe-list.component';

describe('ProjetPfeListComponent', () => {
  let component: ProjetPfeListComponent;
  let fixture: ComponentFixture<ProjetPfeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjetPfeListComponent]
    });
    fixture = TestBed.createComponent(ProjetPfeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
