import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RealEstateAppraisedSectionComponent } from './real-estate-appraised-section.component';

describe('RealEstateAppraisedSectionComponent', () => {
  let component: RealEstateAppraisedSectionComponent;
  let fixture: ComponentFixture<RealEstateAppraisedSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RealEstateAppraisedSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RealEstateAppraisedSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
