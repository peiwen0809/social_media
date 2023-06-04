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
          <tr v-for="post in posts" :key="post.postID" :class="{ 'selected-post': post.isSelected }">
            <!-- <td>{{ post.postID }}</td> -->
            <td>{{ post.user_name }}</td>
            <td>{{ post.content }}</td>
            <td>{{ post.created_at }}</td>
            <td>
              <button @click="commentPost(post)">Comment</button>
            </td>
          </tr>
        </tbody>
      </table>
  
      <h2>Comments</h2>
      <table>
        <thead>
          <tr>
            <!-- <th>Comment ID</th> -->
            <th>User name</th>
            <th>Content</th>
            <th>Created At</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comment in comments" :key="comment.commentID">
            <!-- <td>{{ comment.commentID }}</td> -->
            <td>{{ comment.user_name }}</td>
            <td>{{ comment.content }}</td>
            <td>{{ comment.created_at }}</td>
          </tr>
        </tbody>
      </table>
  
      <!-- 針對貼文新增評論 -->
      <div v-if="selectedPost">
        <textarea v-model="newComment" style="width: 200px; height: 100px;"></textarea>
        <!-- 如果沒登入不能新增評論 -->
        <button @click="addComment" :disabled="!isUserLoggedIn">Add Comment</button>
        <button @click="cancelComment">Cancel</button>
      </div>
    </div>
  </template>
  
  <style scoped>
  .selected-post {
    background-color: rgb(155, 247, 185); /* 設定顏色 */
  }
  </style>
  
  <script>
  import axios from 'axios';
  import DOMPurify from 'dompurify';
  
  export default {
    data() {
      return {
        posts: [],
        selectedPost: null,
        comments: [],
        newComment: ''
      };
    },
    mounted() {
      this.fetchPosts();
    },
    computed: {
      isUserLoggedIn() {
        return !!sessionStorage.getItem('user');
      }
    },
    methods: {
      selectPost(post) {
        // 取消先前選中的貼文
        this.posts.forEach((p) => {
          p.isSelected = false;
        });
  
        // 當前選中的貼文
        post.isSelected = true;
        this.selectedPost = post; // 更新
      },
      fetchPosts() {
        axios.get(`http://localhost:8081/post/listall`)
          .then(response => {
            this.posts = response.data.Content;
          })
          .catch(error => {
            console.error(error);
          });
      },
      commentPost(post) {
        const data = { postID: post.postID };
        axios.post(`http://localhost:8081/comment/list`, data)
          .then(response => {
            this.comments = response.data.Content;
            this.selectPost(post); // 當前選中的貼文
          })
          .catch(error => {
            console.error(error);
          });
      },
      addComment() {
        if (!this.isUserLoggedIn) {
          alert('請先登入');
          return;
        }
        if (!this.selectedPost || this.newComment === '') {
            alert('請輸入內容');
            return;
        }
  
        const data = {
          postID: this.selectedPost.postID,
          content: DOMPurify.sanitize(this.newComment),
          userID: sessionStorage.getItem('user')
        };
  
        axios.post(`http://localhost:8081/comment/create`, data)
          .then(response => {
            // console.log(response);
            if (response.data.Success) {
                this.newComment = ''; // 清空
            } else {
                console.log(response);
            }
            this.commentPost(this.selectedPost); // 刷新
          })
          .catch(error => {
            console.error(error);
          });
      },
      cancelComment() {
        this.newComment = ''; // 清空
      }
    }
  };
  </script>
  