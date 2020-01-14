import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ondemandPipe'
})
export class OndemandPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value != null && value !== undefined) {
      if (args[0] === 1) {

        return value.filter(item => item.cost < 1500000000);
      } else if (args[0] === 2) {

        return value.filter(item => item.cost >= 1500000000 &&  item.cost < 2000000000 );
      } else {

        return value.filter(item => item.cost  >= 2000000000);
      }
    } else {
      return value;
    }
  }

}
