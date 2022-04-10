import { Injectable } from '@angular/core';
import { AuthenticationService } from '@api/api/authentication.service';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { RoleService } from '@api/api/role.service';
import { UserService } from '@api/api/user.service';
import { AuthorDto } from '@api/model/authorDto';
import { BookDto } from '@api/model/bookDto';
import { CategoryDto } from '@api/model/categoryDto';
import { RoleDto } from '@api/model/roleDto';
import { UserDto } from '@api/model/userDto';
import { BehaviorSubject, forkJoin, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ToastService } from '~/app/toast.service';

interface FetchAllOptions<T> {
  operation: Observable<T[]>;
  subject: BehaviorSubject<T[]>;
}

const fetchAll = <T>({ operation, subject }: FetchAllOptions<T>) => {
  operation.pipe(
    tap(roles => {
      subject.next(roles);
    }),
  ).subscribe();
};

const fromEntries = <T>(entries: Array<[string, T]>): Record<string, T> => {
  return entries.reduce((acc, [key, value]) => {
    acc[key] = value;
    return acc;
  }, {} as Record<string, T>);
};

const asMap = <T extends { id: string }>(items: T[]): Record<string, T> => {
  return fromEntries(items.map(item => [item.id, item]));
};

export interface BookVm extends BookDto {
  relations: {
    author: AuthorDto;
    category: CategoryDto;
  };
}

@Injectable()
export class AdminFacade {
  readonly roles$ = new BehaviorSubject<RoleDto[]>([]);
  readonly users$ = new BehaviorSubject<UserDto[]>([]);
  readonly authors$ = new BehaviorSubject<AuthorDto[]>([]);
  readonly categories$ = new BehaviorSubject<CategoryDto[]>([]);
  readonly books$ = new BehaviorSubject<BookVm[]>([]);

  constructor(
    readonly roleService: RoleService,
    readonly userService: UserService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
    readonly bookService: BookService,
    readonly authenticationService: AuthenticationService,
    readonly toastService: ToastService,
  ) {
  }

  getRoles = () => fetchAll({
    operation: this.roleService.getRoles(),
    subject: this.roles$,
  });

  getUsers = () => fetchAll({
    operation: this.userService.getUsers(),
    subject: this.users$,
  });

  getAuthors = () => fetchAll({
    operation: this.authorService.getAuthors(),
    subject: this.authors$,
  });

  getCategories = () => fetchAll({
    operation: this.categoryService.getCategories(),
    subject: this.categories$,
  });

  getBooks = () => {
    forkJoin([this.authorService.getAuthors(), this.categoryService.getCategories(), this.bookService.getBooks()]).pipe(
      tap(([authors, categories, books]) => {
        const authorsMap = asMap(authors);
        const categoriesMap = asMap(categories);

        const vms = books.map((book): BookVm => {
          const author = authorsMap[book.authorId];
          const category = categoriesMap[book.categoryId];
          return { ...book, relations: { author, category } };
        });

        this.books$.next(vms);
      }),
    ).subscribe();
  };

  getBook = (id: string) => this.bookService.getBook(id);
}
