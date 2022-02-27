import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
// feature
import { UserRoutingModule } from './user-routing.module';
import { components } from './components';

@NgModule({
  imports: [CommonModule, UserRoutingModule],
  declarations: [...components],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UserModule {
}
