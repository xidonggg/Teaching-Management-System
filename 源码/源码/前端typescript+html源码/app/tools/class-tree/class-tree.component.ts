import { Component, OnInit } from '@angular/core';
import { NzFormatEmitEvent } from 'ng-zorro-antd';


@Component({
  selector: 'app-class-tree',
  templateUrl: './class-tree.component.html',
  styleUrls: ['./class-tree.component.css']
})
export class ClassTreeComponent implements OnInit {

  defaultCheckedKeys = ['0-0-0'];
  defaultSelectedKeys = ['0-0-0'];
  defaultExpandedKeys = ['0-0', '0-0-0', '0-0-1'];

  nodes = [
    {
      title: 'XX校区',
      key: '0-0',
      expanded: true,
      children: [
        {
          title: '一年级',
          key: '0-0-0',
          children: [
            { title: '1班', key: '0-0-0-0', isLeaf: true },
            { title: '2班', key: '0-0-0-1', isLeaf: true },
            { title: '未分配', key: '0-0-0-2', isLeaf: true }
          ]
        },
        {
          title: '二年级',
          key: '0-0-1',
          children: [
            { title: '1班', key: '0-0-1-0', isLeaf: true },
            { title: '2班', key: '0-0-1-1', isLeaf: true },
            { title: '未分配', key: '0-0-1-2', isLeaf: true }
          ]
        },
        {
          title: '未分配',
          key: '0-0-2',
          isLeaf: true
        }
      ]
    },
    {
      title: 'AB校区',
      key: '0-1',
      children: [
      ]
    },
    {
      title: 'CD校区',
      key: '0-2',
      isLeaf: true
    }
  ];

  nzEvent(event: NzFormatEmitEvent): void {
    console.log(event);
  }

  ngOnInit(): void {}

}
