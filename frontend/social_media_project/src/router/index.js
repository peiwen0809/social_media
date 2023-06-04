import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
  },
  {
    path: '/post',
    name: 'post',
    component: () => import(/* webpackChunkName: "post" */ '../views/PostView.vue')
  },
  {
    path: '/mypost',
    name: 'mypost',
    component: () => import(/* webpackChunkName: "mypost" */ '../views/MyPostView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/RegisterView.vue')
  },
  {
    path: '/createpost',
    name: 'createpost',
    component: () => import(/* webpackChunkName: "createpost" */ '../views/CreatePostView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router


