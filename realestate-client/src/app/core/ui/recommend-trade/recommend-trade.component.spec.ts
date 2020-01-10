import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendTradeComponent } from './recommend-trade.component';

describe('RecommendTradeComponent', () => {
  let component: RecommendTradeComponent;
  let fixture: ComponentFixture<RecommendTradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendTradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendTradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
