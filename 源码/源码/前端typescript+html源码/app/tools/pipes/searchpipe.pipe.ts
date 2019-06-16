import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchpipe'
})
export class SearchpipePipe implements PipeTransform {

  transform(list: any[], field: string, keyword: string): any{

    if(!field || !keyword) {
      // console.log("undefined:");
      return list;
    }
    return list.filter(item => {
      let itemFieldValue = item[field].toLowerCase();
      let temp = itemFieldValue.indexOf(keyword) >= 0;
      // console.log("itemFieldValue:"+itemFieldValue);
      // console.log("temp:"+temp);
      return temp;
    })
  }

}
