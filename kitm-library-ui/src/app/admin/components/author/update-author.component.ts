import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <div class="row">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Update author</h2>
        <app-category-form>
          <button type="submit" class="btn btn-warning" name="update">Update</button>
          <button type="submit" class="btn btn-danger m-1" name="delete">Delete</button>
        </app-category-form>
      </div>
    </div>
  `,
  changeDetection,
})
export class UpdateAuthorComponent {
}
