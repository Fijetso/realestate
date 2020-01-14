import { Pipe, PipeTransform } from '@angular/core';
import { nonAccentVietnamese } from './../../functions/remove-sign';

@Pipe({
  name: 'standardizedSlug'
})
export class StandardizedSlugPipe implements PipeTransform {

  transform(value: any): any {
    if (value) {
      const slug = nonAccentVietnamese(value).replace(' ', '-' );
      return slug;
    } else {
      return value;
    }
  }

}
