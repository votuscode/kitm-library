import { Injectable } from '@angular/core';
import { AuthenticationService } from '@api/api/authentication.service';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ToastService } from '~/app/toast.service';

@Injectable({ providedIn: 'root' })
export class AuthenticationFacade {
  readonly token$ = new BehaviorSubject<string | undefined>(undefined);

  constructor(readonly authenticationService: AuthenticationService, readonly toastService: ToastService) {
  }

  login = () => {
    return this.authenticationService.login({ username: 'admin', password: 'secret' }).pipe(
      tap(({ token }) => {
        this.token$.next(token);
        this.toastService.success('Login successful.');
      }),
    );
  };
}
