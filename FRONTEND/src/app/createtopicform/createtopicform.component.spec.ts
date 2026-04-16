import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatetopicformComponent } from './createtopicform.component';

describe('CreatetopicformComponent', () => {
  let component: CreatetopicformComponent;
  let fixture: ComponentFixture<CreatetopicformComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreatetopicformComponent]
    });
    fixture = TestBed.createComponent(CreatetopicformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
