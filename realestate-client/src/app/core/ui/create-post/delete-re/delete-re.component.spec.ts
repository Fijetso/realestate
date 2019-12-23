import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteReComponent } from './delete-re.component';

describe('DeleteReComponent', () => {
  let component: DeleteReComponent;
  let fixture: ComponentFixture<DeleteReComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteReComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteReComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
