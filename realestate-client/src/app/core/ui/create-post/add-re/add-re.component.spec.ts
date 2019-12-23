import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReComponent } from './add-re.component';

describe('AddReComponent', () => {
  let component: AddReComponent;
  let fixture: ComponentFixture<AddReComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddReComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
