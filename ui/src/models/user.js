import { query as queryUsers, queryCurrent, list as userList } from '../services/user';

export default {
  namespace: 'user',

  state: {
    data: [],
    currentUser: {},
  },

  effects: {
    *fetch(_, { call, put }) {
      const response = yield call(queryUsers);
      yield put({
        type: 'save',
        payload: response,
      });
    },
    *fetchCurrent(_, { call, put }) {
      const response = yield call(queryCurrent);
      yield put({
        type: 'saveCurrentUser',
        payload: response,
      });
    },
    *list(_,{ call, put }){
      const response = yield call(userList,_.payload);
      debugger;
      yield put({
        type: 'userList',
        payload: response
      });
    }
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        list: action.payload,
      };
    },
    saveCurrentUser(state, action) {
      return {
        ...state,
        currentUser: action.payload,
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
