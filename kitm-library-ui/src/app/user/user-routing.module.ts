import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '~/app/core/security/authentication.guard';
// feature
import { BookView, HomeView } from './components';

const routes: Routes = [
  {
    path: '', children: [
      { path: '', component: HomeView },
      { path: 'books/:bookId', component: BookView },
    ],
    canActivate: [AuthenticationGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class UserRoutingModule {
}
