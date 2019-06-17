import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'getIdFromName'
})
export class GetIdFromNamePipe implements PipeTransform {

  transform(districtList: any, args?: any): any {
    if (districtList) {
      return districtList.find(disctrict => disctrict.nameWithType === args).id;
    }
    return null;
  }

}
