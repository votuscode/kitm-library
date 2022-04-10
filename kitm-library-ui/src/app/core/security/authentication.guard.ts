import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';
import { map, switchMap, take } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';

@Injectable({providedIn: 'root'})
export class AuthenticationGuard implements CanActivate {
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.authenticationFacade.token$.pipe(
      take(1),
      switchMap(token => {
        if (token) {
          return of(true);
        } else {
          return this.authenticationFacade.login().pipe(
            map(() => true)
          )
        }
      })
    )
  }

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}
