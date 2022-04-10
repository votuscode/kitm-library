import { Component, OnInit } from '@angular/core';
import { UserDto } from '@api/model/userDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Users</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <app-item-list [items]="adminFacade.users$ | async" [options]="options"></app-item-list>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12 col-lg-offset-3">
        <button type="button" class="btn btn-primary" name="button" routerLink="/admin/users/add">
          Add user
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class UserListComponent implements OnInit{

  options: ItemListOptions<UserDto> = {
    mapper: ({ id, name, username, email }) => {
      return {
        id,
        name,
        description: `${username} (${email})`,
        link: `/admin/users/${id}`,
        info: `Value: ${123}, value: ${123}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getUsers();
  }
}
