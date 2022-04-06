import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// feature
import * as fromComponents from './components';

const routes: Routes = [
  {
    path: '', component: fromComponents.AdminView, children: [
      { path: 'authors', component: fromComponents.AuthorListComponent },
      { path: 'authors/add', component: fromComponents.AddAuthorComponent },
      { path: 'authors/:id', component: fromComponents.UpdateAuthorComponent },
      { path: 'categories', component: fromComponents.CategoryListComponent },
      { path: 'categories/add', component: fromComponents.AddCategoryComponent },
      { path: 'categories/:id', component: fromComponents.UpdateCategoryComponent },
      { path: 'books', component: fromComponents.BookListComponent },
      { path: 'books/add', component: fromComponents.AddBookComponent },
      { path: 'books/:id', component: fromComponents.UpdateBookComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {
}
