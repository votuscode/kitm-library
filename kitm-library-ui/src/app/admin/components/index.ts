import { AdminView } from '~/app/admin/components/admin.view';
import { LayoutComponent } from '~/app/admin/components/layout.component';
import { ItemListComponent } from '~/app/admin/components/item-list.component';
// author
import { AuthorListComponent } from '~/app/admin/components/author/author-list.component';
import { AuthorFormComponent } from '~/app/admin/components/author/author-form.component';
import { AddAuthorComponent } from '~/app/admin/components/author/add-author.component';
import { UpdateAuthorComponent } from '~/app/admin/components/author/update-author.component';
// book
import { BookListComponent } from '~/app/admin/components/book/book-list.component';
import { BookFormComponent } from '~/app/admin/components/book/book-form.component';
import { AddBookComponent } from '~/app/admin/components/book/add-book.component';
import { UpdateBookComponent } from '~/app/admin/components/book/update-book.component';
// category
import { CategoryListComponent } from '~/app/admin/components/category/category-list.component';
import { CategoryFormComponent } from '~/app/admin/components/category/category-form.component';
import { AddCategoryComponent } from '~/app/admin/components/category/add-category.component';
import { UpdateCategoryComponent } from '~/app/admin/components/category/update-category.component';

export const components = [
  AdminView,
  LayoutComponent,
  ItemListComponent,
  // author
  AuthorListComponent,
  AuthorFormComponent,
  AddAuthorComponent,
  UpdateAuthorComponent,
  // category
  CategoryListComponent,
  CategoryFormComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
  // book
  BookListComponent,
  BookFormComponent,
  AddBookComponent,
  UpdateBookComponent,
];

export {
  AdminView,
  // author
  AuthorListComponent,
  AddAuthorComponent,
  UpdateAuthorComponent,
  // book
  BookListComponent,
  AddBookComponent,
  UpdateBookComponent,
  // category
  CategoryListComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
};
