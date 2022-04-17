import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { WishService } from '@api/api/wish.service';
import { forkJoin } from 'rxjs';
import { map, switchMap, withLatestFrom } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { BookVm } from '~/app/shared/components/book-list/book-list.component';
import { asMap } from '~/app/shared/util/as-map';
import { ToastService } from '~/app/toast.service';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-layout>
      <div widget>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
               (input)="search(searchInput.value)" #searchInput>
      </div>
      <h2>Wish list</h2>
      <p>Here you can see your wish list.</p>
      <app-book-list [vms]="vm$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class WishListView {

  readonly vm$ = this.wishService.getWishes().pipe(
    withLatestFrom(this.authenticationFacade.user$),
    switchMap(([wishes, user]) => {
      return forkJoin(wishes.map(wish => this.bookService.getBook(wish.bookId))).pipe(
        map((books): BookVm[] => {
          const bookMap = asMap(books);

          return wishes.map(wish => {
            const book = bookMap[wish.bookId];
            const link = `/books/${wish.bookId}`;
            const ordered = Boolean(book.orderId);

            return { book, link, ordered };
          });
        }),
      );
    }),
  );

  constructor(
    readonly route: ActivatedRoute,
    readonly bookService: BookService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
    readonly wishService: WishService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
  ) {
  }

  search = (value: string) => {
    console.log({ value });
  };
}
