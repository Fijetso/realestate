import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotRealEstateSectionComponent } from './hot-real-estate-section.component';

describe('HotRealEstateSectionComponent', () => {
  let component: HotRealEstateSectionComponent;
  let fixture: ComponentFixture<HotRealEstateSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotRealEstateSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotRealEstateSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
