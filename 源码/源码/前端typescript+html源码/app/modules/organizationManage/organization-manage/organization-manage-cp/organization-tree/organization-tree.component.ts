import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Organization} from '../../../organization-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-organization-tree',
  templateUrl: './organization-tree.component.html',
  styleUrls: ['./organization-tree.component.css']
})
export class OrganizationTreeComponent implements OnInit {

  @Input() treelists: any;
  @Output() treeItem: EventEmitter<Organization> = new EventEmitter<Organization>();
  private organization: Organization =  new Organization(1, 'dgdf区', '','',true, 0, []);
  constructor(private router: Router) { }

  ngOnInit() {
  }
  setOpen(item: Organization) {
    item.open = true;
  }
  setClose(item: Organization) {
    item.open = false;
  }
  showItem(item: Organization) {
    console.log(item.name);
    this.organization = item;
    this.router.navigateByUrl('/organizationalMessage/' + item.id);
    this.treeItem.emit(this.organization);

  }

}
