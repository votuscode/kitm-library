import { Component } from '@angular/core';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';

@Component({
  template: `
    <div>
      <!-- Header navigation -->
      <nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
        <div class="container-fluid">
          <a class="navbar-brand" [href]="home">
            <img src="/assets/library.svg" alt="Library" style="height: 1.5rem">
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                  data-bs-target="#navbarSupportedContent"
                  aria-controls="navbarSupportedContent" aria-expanded="false"
                  aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" [href]="home">Home</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-bs-toggle="dropdown"
                   aria-expanded="false">
                  Admin
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li *ngFor="let item of items">
                    <a class="dropdown-item" [href]="item.href">{{ item.label }}</a>
                  </li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li>
                    <!-- Logout button -->
                    <button type="submit" class="dropdown-item" (click)="authenticationFacade.logout()">Logout</button>
                  </li>
                </ul>
              </li>
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>

      <!-- Main container -->
      <div class="container-fluid" style="height: calc(100vh - 6rem); overflow: hidden">
        <div class="row">
          <!-- Left menu -->

          <!-- Content -->
          <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
              <div class="tab-pane fade show active" role="tabpanel" aria-labelledby="v-pills-home-tab">
                <main class="container">
                  Main
                </main>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
})
export class UserView {
  home = '/';

  items = [
    { label: 'Books', href: '/books' },
  ];

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}
