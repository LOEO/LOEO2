import request from '../utils/request';
import { stringify } from 'qs';

export async function query(userId) {
  debugger;
  return request(`/api/users/${userId}/menus`);
}

