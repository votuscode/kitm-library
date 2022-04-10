import { Component } from '@angular/core';
import { ItemListOptions } from '~/app/admin/components/item-list.component';
import { changeDetection } from '~/change-detection.strategy';
import { AdminFacade, Author } from '~/app/admin/admin.facade';

@Component({
  selector: 'app-category-list',
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Authors</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <app-item-list [items]="adminFacade.authors" [options]="options"></app-item-list>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12 col-lg-offset-3">
        <button type="button" class="btn btn-primary" name="button" routerLink="/admin/authors/add">
          Add author
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class AuthorListComponent {

  options: ItemListOptions<Author> = {
    mapper: ({ id, name, description, categories, books }) => {
      return {
        id,
        name,
        description,
        link: `/admin/authors/${id}`,
        info: `Categories: ${categories}, books: ${books}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }
}
