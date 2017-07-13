const routers = [{
    path: '/index',
    meta: {
        title: '111'
    },
    component: (resolve) => require(['./views/index.vue'], resolve)
},
{
    path: '/app',
    meta: {
        title: '111'
    },
    component: (resolve) => require(['./app.vue'], resolve)
}
,
{
    path: '/login',
        meta
:
    {
        title: '111'
    }
,
    component: (resolve) =
>
    require(['./views/login.vue'], resolve)
}];
export default routers;