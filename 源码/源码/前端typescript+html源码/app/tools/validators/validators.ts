import {FormControl} from '@angular/forms';

export function mobileValidator(mobile: FormControl):any {
  let value = (mobile.value || '')+'';
  var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
  let valid = myreg.test(value);
  console.log('mobile是否校验通过：'+valid);
  return valid ? null : {mobile:{description:'手机号不合法'}};
}
