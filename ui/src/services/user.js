import request from '../utils/request';
import { stringify } from 'qs';

export async function query() {
  return request('/api/users');
}

export async function queryCurrent() {
  return request('/api/users/currentUser');
}

export async function list(params) {
  console.log(params);
  debugger;
  return request(`/api/users?${stringify(params)}`);
}
