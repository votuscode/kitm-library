import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface CreateUserDto {
  name: string;
  email: string;
  username: string;
  roles: ReadonlyArray<string>;
}

export interface UserDto {
  id: string;
  name: string;
  email: string;
  username: string;
  roles: ReadonlyArray<string>;
  createdAt: string;
  updatedAt: string;
}

@Injectable()
export class UserService {
  constructor(readonly httpClient: HttpClient) {
  }

  findAll = () => {
    return this.httpClient.get<UserDto[]>('/api/user');
  }

  createOne = (createUserDto: CreateUserDto) => {
    return this.httpClient.post<UserDto>('/api/user', createUserDto);
  }
}
