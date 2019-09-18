import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'getDistrictNameFromId'
})
export class GetDistrictNameFromIdPipe implements PipeTransform {
  transform(districtList: any[], districtId?: any ): any {
    if (districtList) {
      // console.log(districtList.find(disctrict => disctrict.id === districtId).nameWithType.replace('Quận ', 'Q.'));
      return districtList.find(disctrict => disctrict.id === districtId).nameWithType.replace('Quận ', 'Q.');
    }
    return null;
  }
}
