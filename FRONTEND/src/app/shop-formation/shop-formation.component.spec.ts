import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopFormationComponent } from './shop-formation.component';

describe('ShopFormationComponent', () => {
  let component: ShopFormationComponent;
  let fixture: ComponentFixture<ShopFormationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShopFormationComponent]
    });
    fixture = TestBed.createComponent(ShopFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
