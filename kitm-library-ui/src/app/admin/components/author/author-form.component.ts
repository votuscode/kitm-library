import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-author-form',
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
          <label class="col-lg-3 control-label">Description</label>
          <div class="col-lg-9">
            <textarea class="form-control" name="description"></textarea>
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
export class AuthorFormComponent {
}
