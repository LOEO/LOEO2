import { stringify } from 'qs';
import request from '../utils/request';

export async function login(username,password,rememberMe) {
  return request("/login",{
    body:{username,password,rememberMe},
    method:"POST"
  });
}
