import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressourceComponent } from './addressource.component';

describe('AddressourceComponent', () => {
  let component: AddressourceComponent;
  let fixture: ComponentFixture<AddressourceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddressourceComponent]
    });
    fixture = TestBed.createComponent(AddressourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
