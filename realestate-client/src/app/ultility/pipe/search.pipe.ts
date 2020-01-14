import { Pipe, PipeTransform } from '@angular/core';
import { nonAccentVietnamese } from '../functions/remove-sign';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    // console.log(args);
    if (value != null) {
      if (args[0][0] === undefined) {
        return value.filter(elem => {
          if ( elem !== null && elem !== undefined) {
            return elem.realEstateKind.id === args[0][1];
          }
        });
      } else {
        return value.filter( elem => {
          if (elem != null) {
            console.log(args);
            const result = nonAccentVietnamese(elem.description).indexOf(nonAccentVietnamese(args[0][0])) > -1
            && elem.realEstateKind.id === args[0][1];
            console.log(result);
            return result;
          }
        });
      }
    } else {
      return value;
    }
  }

}
