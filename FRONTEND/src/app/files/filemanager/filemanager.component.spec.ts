import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilemanagerComponent } from './filemanager.component';

describe('FilemanagerComponent', () => {
  let component: FilemanagerComponent;
  let fixture: ComponentFixture<FilemanagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilemanagerComponent]
    });
    fixture = TestBed.createComponent(FilemanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
