import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tradekindPipe'
})
export class TradekindPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (args && value ) {
      return value.filter(trade => {
        return trade.tradeKind.id === (args[0]);
      });
    } else {
      return value;
    }
  }

}
