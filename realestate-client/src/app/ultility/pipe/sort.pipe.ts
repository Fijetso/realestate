import { Pipe, PipeTransform } from '@angular/core';
import { nonAccentVietnamese } from '../functions/remove-sign';

@Pipe({
  name: 'sort'
})
export class SortPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value) {
      return value.sort((a, b) => {
        if (nonAccentVietnamese(a.nameWithType) > nonAccentVietnamese(b.nameWithType))
        { return 1; }
        else if (nonAccentVietnamese(a.nameWithType) === nonAccentVietnamese(b.nameWithType)) { return 0; }
        else { return -1; }
       });
    } else {
      return value;
    }
  }

}
