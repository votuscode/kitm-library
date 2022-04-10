import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';
import { AdminFacade } from '~/app/admin/admin.facade';

@Component({
  selector: 'app-category-list',
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Categories</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <ul class="list-group">
          <a [routerLink]="category.meta.link"
             class="list-group-item list-group-item-action flex-column align-items-start"
             *ngFor="let category of categories">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1" [innerText]="category.name">Category</h5>
              <small [innerText]="category.books">Books total</small>
            </div>
            <p class="mb-1" [innerText]="category.description">Description</p>
            <small>Donec id elit non mi porta.</small>
          </a>
        </ul>
      </div>
    </div>

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

    <div class="row">
      <div class="col-lg-12 col-lg-offset-3">
        <button type="button" class="btn btn-primary" name="button" routerLink="/admin/categories/add">
          Add category
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class CategoryListComponent {

  get categories() {
    return this.adminFacade.categories.map(category => {
      return {
        ...category,
        meta: {
          link: `/admin/categories/${category.id}`,
        },
      };
    });
  }

  constructor(readonly adminFacade: AdminFacade) {
  }
}
