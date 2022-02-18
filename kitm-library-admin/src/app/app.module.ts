import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ApiModule } from '@api/api.module';
import { Configuration } from '@api/configuration';
import { provideAuthenticationInterceptor } from '~/app/authentication.interceptor';
import { provideHttpErrorInterceptor } from '~/app/http-error.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ApiModule.forRoot(() => {
      return new Configuration({
        basePath: '',
      });
    }),
  ],
  providers: [
    provideHttpErrorInterceptor(),
    provideAuthenticationInterceptor(),
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {
}
