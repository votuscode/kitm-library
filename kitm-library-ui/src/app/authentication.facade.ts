import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationFacade {
  readonly token$ = new BehaviorSubject<string | undefined>(undefined);
}
