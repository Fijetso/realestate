import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'FilterPipe'
})
export class FilterPipe implements PipeTransform {

  transform(list: any[], searchTerm: string): any {
    if (searchTerm) {
      searchTerm = searchTerm.toLowerCase();
      return list.filter( elem => {
        return elem.name.toLowerCase().indexOf(searchTerm) > -1 ||
        elem.email.toLowerCase().indexOf(searchTerm) > -1 || elem.userKind.name.toLowerCase().indexOf(searchTerm) > -1
        ;
      });
    } else {
      return list;
    }
  }

}
