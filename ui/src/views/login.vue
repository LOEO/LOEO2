<template>
    <form v-on:submit.prevent>
        <input type="text" name="username" v-model="username" placeholder="用户名">
        </br>
        <input type="password" name="password" v-model="password" placeholder="密码">
        <input type="submit" value="登陆" @click="click">
    </form>
</template>
<script>
    import api from '../libs/api.js';
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
                api.login(this.username, this.password, function (response) {
                    alert(JSON.stringify(response));
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