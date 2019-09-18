import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FloatingMenuItemComponent } from './floating-menu-item.component';

describe('FloatingMenuItemComponent', () => {
  let component: FloatingMenuItemComponent;
  let fixture: ComponentFixture<FloatingMenuItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FloatingMenuItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FloatingMenuItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
