<template>
  <div>
    <h2>Registration Form</h2>
    <form @submit.prevent="registerUser">
      <div>
        <label for="user_name">Username:</label>
        <input type="text" id="user_name" v-model="user_name" required>
      </div>
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div>
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" v-model="phone" required>
      </div>
      <div>
        <label for="biography">Biography:</label>
        <textarea id="biography" v-model="biography" rows="4"></textarea>
      </div>
      <div>
        <button type="submit">Register</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import router from '@/router';

export default {
  data() {
    return {
      user_name: '',
      email: '',
      password: '',
      phone: '',
      biography: ''
    };
  },
  methods: {
    registerUser(event) {
      event.preventDefault();
      const userData = {
        user_name: this.user_name,
        email: this.email,
        password: this.password,
        phone: this.phone,
        biography: this.biography
      };
  
        axios.post('http://localhost:8081/users/register', userData)
          .then(response => {
            if (response.data.Success){
              window.alert("註冊成功，請重新登入");
              router.push('/login');  // 跳轉到登入(login)頁面
            }else {
              window.alert("註冊失敗");
            }            
          })
          .catch(error => {
            window.alert("註冊失敗");
            console.error(error);
          });

      // 清除表單內容
      this.user_name = '';
      this.email = '';
      this.password = '';
      this.phone = '';
      this.biography = '';
    }
  }
};
</script>
