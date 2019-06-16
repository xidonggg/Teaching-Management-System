import { Injectable } from '@angular/core';

@Injectable()
export class InputHandler  {

  public autoWidth(el:HTMLInputElement){

    console.log(el.value + el.value.length);
    el.style.width =1+ el.value.length +"em";
  }
}
