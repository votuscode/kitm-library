import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// feature
import { UserView } from './components';

const routes: Routes = [
  {
    path: '', children: [
      { path: '', component: UserView },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class UserRoutingModule {
}
