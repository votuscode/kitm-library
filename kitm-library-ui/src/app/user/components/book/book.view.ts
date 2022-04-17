import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { AuthorDto } from '@api/model/authorDto';
import { BookDto } from '@api/model/bookDto';
import { CategoryDto } from '@api/model/categoryDto';
import { forkJoin, of } from 'rxjs';
import { map, switchMap, withLatestFrom } from 'rxjs/operators';
import { changeDetection } from '~/change-detection.strategy';

interface BookVm {
  book: BookDto;
  author: AuthorDto;
  category: CategoryDto;
}

@Component({
  template: `
    <app-layout>
      <div class="row" *ngIf="vm$ | async as vm">
        <div class="col">
          <div class="card" style="width: 18rem">
            <img class="card-img-top" [src]="vm.book.image" alt="Book">
          </div>
        </div>
        <div class="col">
          <h5 class="card-title">{{ vm.book.name }}</h5>
          <p>
            <span class="badge bg-secondary">{{ vm.author.name }}</span>
            <span class="badge bg-primary ms-1">{{ vm.category.name }}</span>
          </p>
          <p class="card-text">{{ vm.book.description }}</p>
          <p>
            <button class="btn btn-outline-success" type="button">
              🛍 Order
            </button>
            <button class="btn btn-outline-success ms-1" type="button">
              💝 Wish list
            </button>
          </p>
        </div>
      </div>
    </app-layout>
  `,
  changeDetection,
})
export class BookView {

  readonly vm$ = this.bookService.getBook(this.route.snapshot.params.bookId).pipe(
    switchMap(book => {
      return forkJoin([this.authorService.getAuthor(book.authorId), this.categoryService.getCategory(book.categoryId)]).pipe(
        map(([author, category]): BookVm => {
          return { book, author, category };
        }),
      );
    }),
  );

  constructor(
    readonly route: ActivatedRoute,
    readonly bookService: BookService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
  ) {
  }
}
