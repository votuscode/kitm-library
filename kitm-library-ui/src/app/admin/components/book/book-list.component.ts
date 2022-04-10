import { Component, OnInit } from '@angular/core';
import { AdminFacade, BookVm } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-list',
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Books</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <app-item-list [items]="adminFacade.books$ | async" [options]="options"></app-item-list>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12 col-lg-offset-3">
        <button type="button" class="btn btn-primary" name="button" routerLink="/admin/books/add">
          Add book
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class BookListComponent implements OnInit {

  options: ItemListOptions<BookVm> = {
    mapper: ({ id, name, description, relations }) => {
      return {
        id,
        name,
        description,
        link: `/admin/books/${id}`,
        info: `Author: ${relations.author.name}, category: ${relations.category.name}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getBooks();
  }
}
