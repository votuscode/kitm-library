import { AddBookComponent } from '~/app/admin/components/add-book.component';
import { AdminView } from '~/app/admin/components/admin.view';
import { BookFormComponent } from '~/app/admin/components/book-form.component';
import { BookListComponent } from '~/app/admin/components/book-list.component';
import { LayoutComponent } from '~/app/admin/components/layout.component';
import { CategoryListComponent } from '~/app/admin/components/category-list.component';
import { CategoryFormComponent } from '~/app/admin/components/category-form.component';
import { AddCategoryComponent } from '~/app/admin/components/add-category.component';
import { UpdateBookComponent } from '~/app/admin/components/update-book.component';
import { UpdateCategoryComponent } from '~/app/admin/components/update-category.component';

export const components = [
  AdminView,
  LayoutComponent,
  CategoryListComponent,
  CategoryFormComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
  BookListComponent,
  BookFormComponent,
  AddBookComponent,
  UpdateBookComponent,
];

export {
  AdminView,
  CategoryListComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
  BookListComponent,
  AddBookComponent,
  UpdateBookComponent,
};
