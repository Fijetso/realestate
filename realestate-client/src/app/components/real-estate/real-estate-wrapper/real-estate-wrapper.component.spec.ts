import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RealEstateWrapperComponent } from './real-estate-wrapper.component';

describe('RealEstateWrapperComponent', () => {
  let component: RealEstateWrapperComponent;
  let fixture: ComponentFixture<RealEstateWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RealEstateWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RealEstateWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
