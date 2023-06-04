<template>
    <div>
      <h2>Create Post</h2>
      <form @submit="createPost">
        <label for="content">Content:</label>
        <div>
            <textarea ref="postContent" v-model="newPostContent" style="width: 400px; height: 200px;" required></textarea>
        </div>
        <button type="submit" :disabled="!isUserLoggedIn">Confirm</button>
        <button type="button" @click="cancel">Cancel</button>
      </form>
    </div>
</template>
  
<script>
import axios from 'axios';
import router from '@/router';
import DOMPurify from 'dompurify';


export default {
    computed: {
        isUserLoggedIn() {
            return !!sessionStorage.getItem('user');
        }
    },
    methods: {
        createPost(event) {
        event.preventDefault();

        const data = {
            userID: sessionStorage.getItem('user'),
            content: DOMPurify.sanitize(this.newPostContent)
        };

        axios.post('http://localhost:8081/post/create', data)
            .then(response => {
                // 貼文新增成功的處理邏輯
                console.log(response.data);
                this.newPostContent = ''; // 清空輸入框
                router.push('/mypost');
            })
            .catch(error => {
                // 貼文新增失敗的處理邏輯
                console.error(error);
            });
        },
        cancel() {
            this.newPostContent = '';
            this.$refs.postContent.value = '';
        }
    }
};

</script>
  