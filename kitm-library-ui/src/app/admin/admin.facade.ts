import { Injectable } from '@angular/core';

interface Category {
  id: string;
  name: string;
  description: string;
  books: number;
}

interface Book {
  id: string;
  name: string;
  description: string;
  pages: number;
  isbn: string;
  image: string;
  categoryId: string;
}

@Injectable()
export class AdminFacade {
  categories: Category[] = [
    {
      id: 'category-1',
      name: 'Category #1',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      books: 123,
    },
    {
      id: 'category-2',
      name: 'Category #2',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      books: 123,
    },
    {
      id: 'category-3',
      name: 'Category #3',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      books: 123,
    },
  ];

  books: Book[] = [
    {
      id: 'book-1',
      name: 'Book #1',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      pages: 123,
      isbn: 'ISBN-123-123-123-123',
      image: 'http://image.url',
      categoryId: 'category-1',
    },
    {
      id: 'book-2',
      name: 'Book #2',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      pages: 123,
      isbn: 'ISBN-123-123-123-123',
      image: 'http://image.url',
      categoryId: 'category-2',
    },
    {
      id: 'book-3',
      name: 'Book #3',
      description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio, velit?',
      pages: 123,
      isbn: 'ISBN-123-123-123-123',
      image: 'http://image.url',
      categoryId: 'category-3',
    },
  ];
}
