import { stringify } from 'qs';
import request from '../utils/request';

export async function login(params) {
  debugger;
  return request("/api/login",{
    body:params,
    method:"POST"
  });
}
