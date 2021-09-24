import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface CreateRoleDto {
  name: string;
}

export interface RoleDto {
  name: string;
}

@Injectable()
export class RoleService {
  constructor(readonly httpClient: HttpClient) {
  }

  findAll = () => {
    return this.httpClient.get<RoleDto[]>('/api/role');
  }

  createOne = (createRoleDto: CreateRoleDto) => {
    return this.httpClient.post<RoleDto>('/api/role', createRoleDto)
  }
}
