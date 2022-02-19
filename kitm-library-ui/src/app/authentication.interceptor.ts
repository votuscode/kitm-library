import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap, take } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/authentication.facade';

@Injectable()
class AuthenticationInterceptor implements HttpInterceptor {
  intercept = (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> => {
    return this.authenticationFacade.token$.asObservable().pipe(
      take(1),
      switchMap(token => {
        if (req.url === '/api/public/login') {
          return next.handle(req);
        }

        return next.handle(req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + token)
        }));
      }),
    );
  };

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}

export const provideAuthenticationInterceptor = (): Provider => ({
  provide: HTTP_INTERCEPTORS,
  useClass: AuthenticationInterceptor,
  multi: true,
});
