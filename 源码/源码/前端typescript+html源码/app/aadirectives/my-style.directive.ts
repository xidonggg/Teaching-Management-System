import {Directive, ElementRef, HostListener, Input, OnInit} from '@angular/core';
import {LoginServiceService} from '../login-service.service';

@Directive({
  selector: '[appMyStyle]'
})
export class MyStyleDirective{

  @Input('appMyStyle') authority:string;

  el: ElementRef;

  private myauthorities :string[] = [];
  private needAuthority: string[] = [];
  constructor(el: ElementRef) {
    this.el = el;
  }
  ngOnInit(){
    var storage  =  window.localStorage;
    var index = storage.getItem("authority");
    console.log("我的权限:");
    if(index != null){
      this.myauthorities = index.split(",");
      console.log(this.myauthorities);
      console.log("需要的权限");
      console.log(this.authority);
      console.log(this.el.nativeElement.hidden);
      console.log(this.el.nativeElement);
      this.needAuthority = this.authority.split(",");
      for(let i = 0; i < this.needAuthority.length; i++){
        this.authority = this.needAuthority[i];
        if(this.authority.startsWith("!")){
          this.authority = this.authority.substr(1,this.authority.length);
          console.log(this.authority);
          this.el.nativeElement.hidden = false;
          for(let i = 0; i < this.myauthorities.length; i++){
            if(this.authority == this.myauthorities[i]){
              this.el.nativeElement.hidden = true;
            }
          }
        }else{
          this.el.nativeElement.hidden = true;
          for(let i = 0; i < this.myauthorities.length; i++){
            if(this.authority == this.myauthorities[i]){
              this.el.nativeElement.hidden = false;
            }
          }
        }
      }
      console.log(this.el.nativeElement);
      console.log(this.el.nativeElement.hidden);
    }

  }
  // @HostListener('mouseenter')onMouseEnter(){
  //   console.log(this.styleColor);
  //   this.highlight(this.styleColor||this.defaultColor||'red');
  // }
  // @HostListener('mouseleave')onMouseLeave(){
  //   this.highlight(null);
  // }
  // @HostListener('dblclick')onDblClick(){
  //   this.el.nativeElement.style.display='none';
  // }
  // private highlight(color:string){
  //   this.el.nativeElement.style.backgroundColor=color;
  // }
}
