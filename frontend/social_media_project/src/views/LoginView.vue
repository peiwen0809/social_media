<template>
    <div>
      <h2>Login</h2>
      <form @submit="login">
        <div>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" v-model="phone">
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password">
        </div>
        
        <button type="submit">Login</button>
    
      </form>
  
      <button @click="redirectToRegister">Register</button>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import router from '@/router';
  
  export default {
    data() {
      return {
        phone: '',
        password: ''
      };
    },
    mounted() {
      this.leave();  // 登入成功後跳轉到貼文(post)頁面
    },
    methods: {
        leave() {
            if (sessionStorage.getItem('user') != null){
                router.push('/post');
            }
        },
        login(event) {
            event.preventDefault();
            const userData = {
                phone: this.phone,
                password: this.password
            };
  
            axios.post('http://localhost:8081/users/login', userData)
            .then(response => {
                if (response.data.Success) {
                    sessionStorage.setItem('user', JSON.stringify(response.data.Content.userID));
                    sessionStorage.setItem('user_name', JSON.stringify(response.data.Content.user_name));
                    window.alert('Login SuccessFul');
                    location.reload(); // 重新載入頁面
                }else {
                    window.alert('Login Failed');
                    this.password = '';
                }            
            })
            .catch(error => {
                window.alert('Failed');
                console.error(error);
          });
      },
      redirectToRegister() {  // 跳轉到註冊(register)頁面
        router.push('/register');
      }
    }
  };
  </script>
  