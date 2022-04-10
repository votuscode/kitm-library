import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';
import { AdminFacade } from '~/app/admin/admin.facade';

@Component({
  selector: 'app-category-list',
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Books</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <ul class="list-group">
          <a [routerLink]="book.meta.link"
             class="list-group-item list-group-item-action flex-column align-items-start"
             *ngFor="let book of books">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1" [innerText]="book.name">Name</h5>
              <small [innerText]="book.pages">Pages total</small>
            </div>
            <p class="text-muted mb-1" [innerText]="book.description">Description</p>
            <small [innerText]="book.meta.category">Category</small>
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
          Add book
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class BookListComponent {

  get books() {
    const categories = this.adminFacade.categories.reduce((acc, { id, name }) => {
      return { ...acc, [id]: name };
    }, {} as Record<string, string>);

    return this.adminFacade.books.map(book => {
      return {
        ...book,
        meta: {
          category: categories[book.categoryId],
          link: `/admin/books/${book.id}`,
        },
      };
    });
  }

  constructor(readonly adminFacade: AdminFacade) {
  }
}
