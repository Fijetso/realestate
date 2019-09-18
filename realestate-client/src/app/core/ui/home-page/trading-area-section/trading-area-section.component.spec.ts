import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradingAreaSectionComponent } from './trading-area-section.component';

describe('TradingAreaSectionComponent', () => {
  let component: TradingAreaSectionComponent;
  let fixture: ComponentFixture<TradingAreaSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradingAreaSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradingAreaSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
