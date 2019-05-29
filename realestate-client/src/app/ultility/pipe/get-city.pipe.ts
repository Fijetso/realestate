import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'getCity'
})
export class GetCityPipe implements PipeTransform {

  transform(value: any, id?: number): any {
    return null;
  }

}
