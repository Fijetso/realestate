import { Pipe, PipeTransform } from '@angular/core';
import { nonAccentVietnamese } from '../functions/remove-sign';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (args[0] === undefined) {
      return value;
    } else {
      return value.filter( elem => {
        return nonAccentVietnamese(elem.description.toLowerCase()).indexOf(nonAccentVietnamese(args[0])) > -1;
      });
    }
  }

}
