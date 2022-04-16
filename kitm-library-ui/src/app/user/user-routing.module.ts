import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '~/app/core/security/authentication.guard';
// feature
import { UserView } from './components';

const routes: Routes = [
  {
    path: '', children: [
      { path: '', component: UserView, canActivate: [AuthenticationGuard] },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class UserRoutingModule {
}
