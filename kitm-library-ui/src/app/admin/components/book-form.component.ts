import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';
import { AdminFacade } from '~/app/admin/admin.facade';

@Component({
  selector: 'app-book-form',
  template: `
    <form action="#" method="post" class="form-horizontal">
      <fieldset>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Name</label>
          <div class="col-lg-9">
            <input type="text" class="form-control" name="name"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Category</label>
          <div class="col-lg-9">
            <select class="form-control">
              <option *ngFor="let category of adminFacade.categories" [innerText]="category.name">Category</option>
            </select>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Description</label>
          <div class="col-lg-9">
            <textarea class="form-control" name="description"></textarea>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">ISBN</label>
          <div class="col-lg-9">
            <input class="form-control" name="isbn"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Image</label>
          <div class="col-lg-9">
            <input class="form-control" name="image"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Pages</label>
          <div class="col-lg-9">
            <input class="form-control" name="pages"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <div class="col-lg-12 col-lg-offset-3">
            <ng-content></ng-content>
          </div>
        </div>
      </fieldset>
    </form>
  `,
  changeDetection,
})
export class BookFormComponent {
  constructor(readonly adminFacade: AdminFacade) {
  }
}
