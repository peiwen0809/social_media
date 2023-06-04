<template>
  <div>
    <nav>
      <router-link to="/post">Post</router-link> |
      <router-link to="/mypost">MyPost</router-link> |
      <router-link to="/createpost">CreatePost</router-link> |

      <template v-if="!isLoggedIn">
        <router-link to="/login">Login/Register</router-link>
      </template>
      <template v-else>
        <span>Welcome, {{ getUsername() }}</span>
        <button @click="logout">Logout</button>
      </template>

    </nav>
    <router-view/>
  </div>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return !!sessionStorage.getItem('user');
    }
  },
  methods: {
    getUsername() {
      const user = sessionStorage.getItem('user_name');
      if (user) {
        const userData = JSON.parse(user);
        return userData;
      }
      return '';
    },
    logout() {
      // 清空 sessionStorage
      sessionStorage.removeItem('user');
      // 使用瀏覽器的預設彈出視窗顯示登出成功訊息
      window.alert('Logout Successful');
      location.reload(); // 重新載入頁面
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
