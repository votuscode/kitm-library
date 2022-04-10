import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
// feature
import { AdminRoutingModule } from './admin-routing.module';
import { AdminFacade } from './admin.facade';
import { components } from './components';

@NgModule({
  imports: [CommonModule, AdminRoutingModule],
  declarations: [...components],
  providers: [AdminFacade]
})
export class AdminModule {
}
