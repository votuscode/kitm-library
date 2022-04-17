import { Component } from '@angular/core';
import { BookService } from '@api/api/book.service';
import { BookDto } from '@api/model/bookDto';
import { map } from 'rxjs/operators';
import { changeDetection } from '~/change-detection.strategy';

interface BookVm {
  dto: BookDto,
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
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" (input)="search(searchInput.value)" #searchInput>
      </div>
      <div class="row">
        <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6" *ngFor="let vm of vm$ | async">
          <div class="card mt-3" >
            <a [routerLink]="vm.link">
              <img class="card-img-top" [src]="vm.dto.image" alt="Book">
            </a>
            <div class="card-body" >
              <h6 class="card-title">{{ vm.dto.name }}</h6>
              <p class="card-text" >{{ vm.dto.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </app-layout>
  `,
  changeDetection,
})
export class HomeView {

  readonly vm$ = this.bookService.getBooks().pipe(
    map(dtos => {
      return dtos.map(dto => {
        return {
          dto,
          link: `/books/${dto.id}`,
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
