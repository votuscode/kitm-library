import { ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthenticationService } from '@api/api/authentication.service';
import { RoleService } from '@api/api/role.service';
import { UserService } from '@api/api/user.service';
import { RoleDto } from '@api/model/roleDto';
import { UserDto } from '@api/model/userDto';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/authentication.facade';
import { ToastService } from '~/app/toast.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit {
  title = 'kitm-library-admin';

  readonly roles$ = new BehaviorSubject<RoleDto[]>([]);
  readonly users$ = new BehaviorSubject<UserDto[]>([]);

  constructor(
    readonly roleService: RoleService,
    readonly userService: UserService,
    readonly authenticationService: AuthenticationService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
  ) {
  }

  ngOnInit() {
    this.authenticationService.login({ username: 'admin', password: 'secret' }).pipe(
      tap(({ token }) => {
        this.authenticationFacade.token$.next(token);
        this.toastService.success('Login successful.');
        this.findRoles();
        this.findUsers();
      }),
    ).subscribe();
  }

  findRoles = () => {
    this.roleService.getRoles().pipe(
      tap(roles => {
        this.roles$.next(roles);
      }),
    ).subscribe();
  };

  findUsers = () => {
    this.userService.getUsers().pipe(
      tap(users => {
        this.users$.next(users);
      }),
    ).subscribe();
  };

  createRoles = () => {
    ['SUPER', 'USER'].forEach(name => {
      this.roleService.createRole({ name }).pipe(
        tap(() => {
          this.findRoles();
          this.findUsers();
        }),
      ).subscribe();
    });
  };

  createUsers = () => {
    const users = [
      {
        name: 'Diego del Morao',
        email: 'diego.del.morao@admin.com',
        username: 'diego.del.morao',
        roles: ['SUPER'],
      },
      {
        name: 'John Doe',
        email: 'john.doe@domain.com',
        username: 'john.doe',
        roles: ['USER'],
      },
      {
        name: 'Jane Doe',
        email: 'jane.doe@domain.com',
        username: 'jane.doe',
        roles: ['USER'],
      },
    ];

    users.forEach(user => {
      this.userService.createUser({
        ...user,
        passwordOriginal: 'secret',
        passwordConfirmation: 'secret',
      }).pipe(
        tap(() => {
          this.findRoles();
          this.findUsers();
        }),
      ).subscribe();
    });
  };
}
