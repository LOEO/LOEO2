const routers = [{
    path: '/',
    meta: {
        title: '111'
    },
    component: (resolve) => require(['./views/index.vue'], resolve)
},{
    path: '/app',
    meta: {
        title: '111'
    },
    component: (resolve) => require(['./app.vue'], resolve)
}];
export default routers;