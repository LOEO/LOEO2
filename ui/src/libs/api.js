import axios from "axios";
const ajax = axios.create({
    baseURL: 'http://localhost:8888/api',
    timeout: 3000,
    headers: {'X-Custom-Header': 'foobar'}
});
const api = {
        login: (username, password, success, fail) = > {
        ajax.post("/login", {
        username: username,
        password: password
    }).then(success).catch(fail)
}
}

export default api;