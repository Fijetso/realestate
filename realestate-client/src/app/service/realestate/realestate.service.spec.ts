import { TestBed } from '@angular/core/testing';

import { RealestateService } from './realestate.service';

describe('RealestateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RealestateService = TestBed.get(RealestateService);
    expect(service).toBeTruthy();
  });
});
