<template>
    <form v-on:submit.prevent>
        <input type="text" name="username" v-model="username" placeholder="用户名">
		<br>
        <input type="password" name="password" v-model="password" placeholder="密码">
        <input type="submit" value="登陆" @click="click">
    </form>
</template>
<script>
    import sys from '../api/sys.js';
    import conf from '../config/config.js';
    export default {
        data(){
            return {
                username: "12222",
                password: "2333"
            }
        },
        methods: {
            click: function () {
                console.log(this.username + ":" + this.password);
                sys.login(this.username, this.password, function (data) {
                    sys.getCurUserInfo(function (data) {
                        alert(JSON.stringify(data));
                    }, function (msg) {
                        alert(msg);
                    });
                    //window.location = `${conf.baseUrl}/index`
                }, function (error) {
                    alert(error)
                });
                return false;
            },
            test: function () {
                alert(1);
            }
        }
    }
</script>