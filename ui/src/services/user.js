import request from '../utils/request';
import { stringify } from 'qs';

export async function query() {
  return request('/api/users');
}

export async function queryCurrent() {
  return request('/api/users/currentUser');
}

export async function list(params) {
  return request(`/api/users?${stringify(params)}`);
}

export async function add(params) {
  return request('/api/users', {
    method: 'POST',
    body: params,
  });
}
