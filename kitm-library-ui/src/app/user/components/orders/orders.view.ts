import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { OrderService } from '@api/api/order.service';
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
      <h2>Orders page</h2>
      <p>Here you can see your orders.</p>
      <app-book-list [vms]="vm$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class OrdersView {

  readonly vm$ = this.orderService.getOrders().pipe(
    withLatestFrom(this.authenticationFacade.user$),
    switchMap(([orders, user]) => {
      return forkJoin(orders.map(order => this.bookService.getBook(order.bookId))).pipe(
        map((books): BookVm[] => {
          const bookMap = asMap(books);

          return orders.map(order => {
            const book = bookMap[order.bookId];
            const link = `/books/${order.bookId}`;
            const ordered = order.userId !== user?.id;

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
    readonly orderService: OrderService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
  ) {
  }

  search = (value: string) => {
    console.log({ value });
  };
}
