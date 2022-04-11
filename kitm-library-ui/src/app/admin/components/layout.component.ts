import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-layout',
  template: `
    <nav th:fragment="layout-nav" class="navbar navbar-expand-lg navbar-light bg-light mb-3">
      <div class="container-fluid">
        <a class="navbar-brand" href="/">
          <img src="assets/library.svg" alt="Library" style="height: 1.5rem">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled">Disabled</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                 data-bs-toggle="dropdown"
                 aria-expanded="false">
                Admin
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li *ngFor="let link of links">
                  <a class="dropdown-item" [href]="link.url">{{ link.label }}</a>
                </li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                <li>
                  <form method="post">
                    <input type="hidden"/>
                    <button type="submit" class="dropdown-item">Logout</button>
                  </form>
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

    <div class="container-fluid" style="height: calc(100vh - 5rem); overflow: auto">
      <div class="row">
        <div class="col-3 d-none d-lg-block">
          <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a *ngFor="let link of links" class="nav-link" data-toggle="pill" href="#v-pills-home" role="tab"
               aria-controls="v-pills-home" aria-selected="true" [routerLink]="link.url"
               routerLinkActive="active">{{ link.label }}
            </a>
          </div>
        </div>
        <div class="col-9">
          <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" role="tabpanel" aria-labelledby="v-pills-home-tab">
              <main class="container">
                <ng-content></ng-content>
              </main>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  changeDetection,
})
export class LayoutComponent {
  readonly links = [
    { label: 'Edit roles', url: '/admin/roles' },
    { label: 'Edit users', url: '/admin/users' },
    { label: 'Edit authors', url: '/admin/authors' },
    { label: 'Edit categories', url: '/admin/categories' },
    { label: 'Edit books', url: '/admin/books' },
  ];
}
