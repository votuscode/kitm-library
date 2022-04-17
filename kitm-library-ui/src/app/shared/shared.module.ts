import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// feature
import { components } from './components';

@NgModule({
    imports: [
      CommonModule,
      RouterModule,
    ],
    declarations: [...components],
    exports: [...components],
  },
)
export class SharedModule {
}
