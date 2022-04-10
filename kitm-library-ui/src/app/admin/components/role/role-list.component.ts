import { Component, OnInit } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';
import { RoleDto } from '@api/model/roleDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list.component';

@Component({
  template: `
    <div class="row mb-3">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Roles</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, obcaecati?</p>
        <app-item-list [items]="adminFacade.roles$ | async" [options]="options"></app-item-list>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12 col-lg-offset-3">
        <button type="button" class="btn btn-primary" name="button" routerLink="/admin/roles/add">
          Add role
        </button>
      </div>
    </div>
  `,
  changeDetection,
})
export class RoleListComponent implements OnInit {

  options: ItemListOptions<RoleDto> = {
    mapper: ({ id, name }) => {
      return {
        id,
        name,
        description: '',
        link: `/admin/users/${id}`,
        info: `Value: ${123}, value: ${123}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getRoles();
  }
}
