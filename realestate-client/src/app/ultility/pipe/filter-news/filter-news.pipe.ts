import { Pipe, PipeTransform } from '@angular/core';
import { filter } from 'rxjs/operators';

@Pipe({
  name: 'filterNews'
})
export class FilterNewsPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value == null || value === undefined || args === []) {
      return value;
    } else {
      return value.filter(newsItem => newsItem.category.id === args[0]);
    }
  }

}
