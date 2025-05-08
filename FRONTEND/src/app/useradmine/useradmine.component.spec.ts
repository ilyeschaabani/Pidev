import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UseradmineComponent } from './useradmine.component';

describe('UseradmineComponent', () => {
  let component: UseradmineComponent;
  let fixture: ComponentFixture<UseradmineComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UseradmineComponent]
    });
    fixture = TestBed.createComponent(UseradmineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
