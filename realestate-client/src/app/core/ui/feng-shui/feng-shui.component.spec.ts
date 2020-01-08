import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FengShuiComponent } from './feng-shui.component';

describe('FengShuiComponent', () => {
  let component: FengShuiComponent;
  let fixture: ComponentFixture<FengShuiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FengShuiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FengShuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
