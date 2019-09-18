import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotPlaceItemComponent } from './hot-place-item.component';

describe('HotPlaceItemComponent', () => {
  let component: HotPlaceItemComponent;
  let fixture: ComponentFixture<HotPlaceItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotPlaceItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotPlaceItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
