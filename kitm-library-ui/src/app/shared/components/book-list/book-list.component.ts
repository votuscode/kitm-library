import { Component, Input } from '@angular/core';
import { BookDto } from '@api/model/bookDto';
import { AsyncData } from '~/app/shared/util/async-data';
import { changeDetection } from '~/change-detection.strategy';

export interface BookVm {
  book: BookDto,
  link: string;
  ordered: boolean;
}

@Component({
  selector: 'app-book-list',
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

    .book-ordered {
      filter: opacity(0.5);
    }
  `],
  template: `
    <div class="row">
      <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6" *ngFor="let vm of vms">
        <div class="card mt-3" [ngClass]="vm.ordered ? 'book-ordered' : ''" >
          <a [routerLink]="vm.link">
            <img class="card-img-top" [src]="vm.book.image" alt="Book">
          </a>
          <div class="card-body" >
            <h6 class="card-title">{{ vm.book.name }}</h6>
            <p class="card-text" >{{ vm.book.description }}</p>
          </div>
        </div>
      </div>
    </div>
  `,
  changeDetection,
})
export class BookListComponent {

  @Input() vms: AsyncData<BookVm[]>;
}
