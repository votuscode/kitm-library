import { Component, OnInit } from '@angular/core';
import { AuthorDto } from '@api/model/authorDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-list',
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Authors</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <app-item-list [items]="adminFacade.authors$ | async" [options]="options"></app-item-list>
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
export class AuthorListComponent implements OnInit {

  options: ItemListOptions<AuthorDto> = {
    mapper: ({ id, name, description, books }) => {
      return {
        id,
        name,
        description,
        link: `/admin/authors/${id}`,
        info: `Books: ${books}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getAuthors();
  }
}
