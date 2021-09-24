import { ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { BehaviorSubject, NEVER } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { RoleDto, RoleService } from 'src/app/api/role.service';
import { UserDto, UserService } from 'src/app/api/user.service';

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

  constructor(readonly roleService: RoleService, readonly userService: UserService) {
  }

  ngOnInit() {
    this.findRoles();
    this.findUsers();
  }

  whenFailed = (message: string) => catchError((err: Error) => {
    alert(message);
    console.warn(message, err.message);
    return NEVER;
  });

  findRoles = () => {
    this.roleService.findAll().pipe(
      tap(roles => {
        this.roles$.next(roles);
      }),
      this.whenFailed('Could not get find roles.'),
    ).subscribe();
  };

  findUsers = () => {
    this.userService.findAll().pipe(
      tap(users => {
        this.users$.next(users);
      }),
      this.whenFailed('Could not get find users.'),
    ).subscribe();
  };

  createRoles = () => {
    ['ADMIN', 'USER'].forEach(name => {
      this.roleService.createOne({ name }).pipe(
        tap(() => {
          this.findRoles();
          this.findUsers();
        }),
        this.whenFailed('Could not get create roles.'),
      ).subscribe();
    });
  };

  createUsers = () => {
    const users = [
      {
        name: 'Diego del Morao',
        email: 'diego.del.morao@admin.com',
        username: 'diego.del.morao',
        roles: ['ADMIN'],
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
      this.userService.createOne(user).pipe(
        tap(() => {
          this.findRoles();
          this.findUsers();
        }),
        this.whenFailed('Could not get create users.'),
      ).subscribe();
    });
  };
}
