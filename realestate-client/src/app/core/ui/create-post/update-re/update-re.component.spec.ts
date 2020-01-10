import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateReComponent } from './update-re.component';

describe('UpdateReComponent', () => {
  let component: UpdateReComponent;
  let fixture: ComponentFixture<UpdateReComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateReComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateReComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
