<template>
    <div>
      <table>
        <thead>
          <tr>
            <!-- <th>Post ID</th> -->
            <th>User Name</th>
            <th>Content</th>
            <th>Created At</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="post in posts" :key="post.postID">
            <!-- <td>{{ post.postID }}</td> -->
            <td>{{ post.user_name }}</td>
            <td>
              <template v-if="post.editing">
                <input type="text" v-model="post.editContent">
              </template>
              <template v-else>
                {{ post.content }}
              </template>
            </td>
            <td>{{ post.created_at }}</td>
            <td>
              <template v-if="!post.editing">
                <button @click="editPost(post)">Edit</button>
                <button @click="deletePost(post.postID)">Delete</button>
              </template>
              <template v-else>
                <button @click="savePost(post)">Save</button>
                <button @click="cancelEdit(post)">Cancel</button>
              </template>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        posts: [],
      };
    },
    mounted() {
      console.log(sessionStorage.getItem('user'));
      const userID = sessionStorage.getItem('user');
      if (userID) {
        this.fetchPosts(userID);
      } else {
        this.posts = [];
      }
    },
    methods: {
      fetchPosts(userID) {
        const data = { userID: userID };
        axios
          .post('http://localhost:8081/post/listself', data)
          .then(response => {
            this.posts = response.data.Content;
          })
          .catch(error => {
            console.error(error);
          });
      },
      editPost(post) {
        post.editing = true;
        post.editContent = post.content;
      },
      savePost(post) {
        const updatedContent = post.editContent;
        if (updatedContent.trim() === '') {
          alert('請輸入內容');
          return;
        }
        
        post.content = updatedContent;
        post.editing = false;
        axios
          .post(`http://localhost:8081/post/modify`, { postID: post.postID, content: updatedContent })
          .then(response => {
            console.log(response);
            // 更新成功後，更新對應的 post.content
            post.content = updatedContent;
            // 關閉編輯模式
            post.editing = false;
          })
          .catch(error => {
            console.error(error);
          });
      },
      cancelEdit(post) {
        post.editing = false;
      },
      deletePost(postID) {
        console.log("Delete post:", postID);
        axios
          .delete(`http://localhost:8081/post/delete/${postID}`)
          .then(response => {
            if (response.data.Success) {  // 刪除成功後重新顯示貼文
                const userID = sessionStorage.getItem('user');
                this.fetchPosts(userID);
            }
          })
          .catch(error => {
            console.error(error);
          });
      },
    },
  };
  </script>
  