import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaborativeSpaceComponent } from './collaborative-space.component';

describe('CollaborativeSpaceComponent', () => {
  let component: CollaborativeSpaceComponent;
  let fixture: ComponentFixture<CollaborativeSpaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CollaborativeSpaceComponent]
    });
    fixture = TestBed.createComponent(CollaborativeSpaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
