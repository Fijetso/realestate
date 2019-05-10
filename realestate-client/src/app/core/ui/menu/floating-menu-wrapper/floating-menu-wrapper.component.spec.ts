import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FloatingMenuWrapperComponent } from './floating-menu-wrapper.component';

describe('FloatingMenuWrapperComponent', () => {
  let component: FloatingMenuWrapperComponent;
  let fixture: ComponentFixture<FloatingMenuWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FloatingMenuWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FloatingMenuWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
