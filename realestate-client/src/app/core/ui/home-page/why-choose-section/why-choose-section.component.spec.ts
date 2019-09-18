import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyChooseSectionComponent } from './why-choose-section.component';

describe('WhyChooseSectionComponent', () => {
  let component: WhyChooseSectionComponent;
  let fixture: ComponentFixture<WhyChooseSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhyChooseSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhyChooseSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
