import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradingAreaItemComponent } from './trading-area-item.component';

describe('TradingAreaItemComponent', () => {
  let component: TradingAreaItemComponent;
  let fixture: ComponentFixture<TradingAreaItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradingAreaItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradingAreaItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
