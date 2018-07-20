import {
  query as queryUsers,
  queryCurrent,
  list as userList,
  add as addUser,
} from '../services/user';
import {query as queryMenus} from "../services/menu";

const processMenuData=(menuData,rootId)=>{
  let children = [];
  menuData.forEach(menu=>{
    if(menu.pid === rootId){
      children.push(menu);
      if(!menu.isLeaf){
        menu.children = processMenuData(menuData,menu.id)
      }
    }
  });
  return children;
}

export default {
  namespace: 'user',

  state: {
    data: [],
    currentUser: {},
    menus: []
  },

  effects: {
    * fetch(_, {call, put}) {
      const response = yield call(queryUsers);
      yield put({
        type: 'save',
        payload: response,
      });
    },
    * fetchCurrent(_, {call, put}) {
      const response = yield call(queryCurrent);
      let menus=[];
      if (response.success) {
        const resp = yield call(queryMenus, response.data.id);
        menus = resp.data
      }
      yield put({
        type: 'saveCurrentUserAndMenu',
        user: response.data,
        menus: menus
      });
    },
    * fetchMenus(_, {call, put}) {
      const response = yield call(queryMenus, _.userId);
      yield put({
        type: 'saveMenu',
        payload: response,
      });
    },
    * list(_, {call, put}) {
      const response = yield call(userList, _.payload);
      yield put({
        type: 'userList',
        payload: response,
      });
    },
    * add({payload, callback}, {call, put}) {
      debugger;
      const response = yield call(addUser, payload);
      if (callback) {
        if (!callback(response)) {
          return;
        }
      }
      yield put({
        type: 'save',
        payload: response,
      });
    },
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        list: action.payload,
      };
    },
    saveCurrentUserAndMenu(state, action) {
      return {
        ...state,
        currentUser: action.user,
        menus: processMenuData(action.menus,'0')
      };
    },
    userList(state, action) {
      return {
        ...state,
        data: action.payload.data
      }
    },
    changeNotifyCount(state, action) {
      return {
        ...state,
        currentUser: {
          ...state.currentUser,
          notifyCount: action.payload,
        },
      };
    },
  },
};
