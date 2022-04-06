import { Component, Input } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

export interface ItemListOptions<T> {
  mapper: (data: T) => ListItem;
}

export interface ListItem {
  id: string;
  name: string;
  badge?: string;
  description: string;
  info: string;
  link: string;
}

@Component({
  selector: 'app-item-list',
  template: `
    <ul class="list-group" *ngIf="options">
      <a [routerLink]="item.link"
         class="list-group-item list-group-item-action flex-column align-items-start"
         *ngFor="let item of items.map(options.mapper)">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1" [innerText]="item.name">Name</h5>
          <small *ngIf="item.badge" [innerText]="item.badge">Badge</small>
        </div>
        <p class="mb-1" [innerText]="item.description">Description</p>
        <small [innerText]="item.info">Info</small>
      </a>
    </ul>

    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  `,
  changeDetection,
})
export class ItemListComponent<T> {

  @Input() items!: T[];
  @Input() options!: ItemListOptions<T>;
}
