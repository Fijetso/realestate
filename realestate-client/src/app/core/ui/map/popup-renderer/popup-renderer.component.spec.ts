import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupRendererComponent } from './popup-renderer.component';

describe('PopupRendererComponent', () => {
  let component: PopupRendererComponent;
  let fixture: ComponentFixture<PopupRendererComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopupRendererComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
