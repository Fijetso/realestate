import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostGalleryComponent } from './post-gallery.component';

describe('PostGalleryComponent', () => {
  let component: PostGalleryComponent;
  let fixture: ComponentFixture<PostGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
