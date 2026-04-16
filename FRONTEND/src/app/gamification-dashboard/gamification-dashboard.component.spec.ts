import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamificationDashboardComponent } from './gamification-dashboard.component';

describe('GamificationDashboardComponent', () => {
  let component: GamificationDashboardComponent;
  let fixture: ComponentFixture<GamificationDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GamificationDashboardComponent]
    });
    fixture = TestBed.createComponent(GamificationDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
