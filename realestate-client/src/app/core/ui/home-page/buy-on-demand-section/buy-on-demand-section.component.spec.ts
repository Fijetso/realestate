import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyOnDemandSectionComponent } from './buy-on-demand-section.component';

describe('BuyOnDemandSectionComponent', () => {
  let component: BuyOnDemandSectionComponent;
  let fixture: ComponentFixture<BuyOnDemandSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyOnDemandSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyOnDemandSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
