import { StandardizedSlugPipe } from './standardized-slug.pipe';

describe('StandardizedSlugPipe', () => {
  it('create an instance', () => {
    const pipe = new StandardizedSlugPipe();
    expect(pipe).toBeTruthy();
  });
});
