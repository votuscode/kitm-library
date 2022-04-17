import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { OrderService } from '@api/api/order.service';
import { BookDto } from '@api/model/bookDto';
import { OrderDto } from '@api/model/orderDto';
import { forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { asMap } from '~/app/shared/util/as-map';
import { ToastService } from '~/app/toast.service';
import { changeDetection } from '~/change-detection.strategy';

interface OrderVm {
  order: OrderDto;
  book: BookDto;
  link: string;
}

@Component({
  styles: [`
    .card {
      height: 19rem;
    }

    .card-body {
      overflow: hidden;
    }

    .card-title,
    .card-text {
      font-size: x-small;
    }
  `],
  template: `
    <app-layout>
      <div widget>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
               (input)="search(searchInput.value)" #searchInput>
      </div>
      <h2>Orders page</h2>
      <p>Here you can see your orders.</p>
      <div class="row">
        <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6" *ngFor="let vm of vm$ | async">
          <div class="card mt-3">
            <a [routerLink]="vm.link">
              <img class="card-img-top" [src]="vm.book.image" alt="Book">
            </a>
            <div class="card-body">
              <h6 class="card-title">{{ vm.book.name }}</h6>
              <p class="card-text">{{ vm.book.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </app-layout>
  `,
  changeDetection,
})
export class OrdersView {

  readonly vm$ = this.orderService.getOrders().pipe(
    switchMap(orders => {
      return forkJoin(orders.map(order => this.bookService.getBook(order.bookId))).pipe(
        map((books): OrderVm[] => {
          const bookMap = asMap(books);

          return orders.map(order => {
            const book = bookMap[order.bookId];
            const link = `/books/${order.bookId}`;
            return { order, book, link };
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
