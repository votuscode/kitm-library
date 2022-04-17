import { Component } from '@angular/core';
import { BookService } from '@api/api/book.service';
import { map } from 'rxjs/operators';
import { BookVm } from '~/app/shared/components/book-list/book-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-layout>
      <div widget>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" (input)="search(searchInput.value)" #searchInput>
      </div>
      <app-book-list [vms]="vm$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class HomeView {

  readonly vm$ = this.bookService.getBooks().pipe(
    map(books => {
      return books.map((book): BookVm => {
        return {
          book,
          link: `/books/${book.id}`,
          ordered: Boolean(book.orderId)
        };
      });
    }),
  );

  constructor(readonly bookService: BookService) {
  }

  search = (value: string) => {
    console.log({ value });
  };
}
