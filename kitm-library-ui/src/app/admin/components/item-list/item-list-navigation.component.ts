import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-item-list-navigation',
  template: `
    <nav aria-label="Page navigation">
      <ul class="pagination mt-3">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  `,
  changeDetection,
})
export class ItemListNavigationComponent {
}
