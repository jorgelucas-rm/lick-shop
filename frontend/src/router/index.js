import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/HomePage.vue';
import Login from '@/views/LoginPage.vue';
import Register from '@/views/RegisterPage.vue';
import ProductPage from '@/views/ProductPage.vue';
import LocalPerson from '@/views/LocalPerson.vue';
import RecuperationPage from '@/views/RecuperationPage.vue';
import DetailsPage from '@/views/DetailsPage.vue';
import ProfilePage from '@/views/ProfilePage.vue';
import AdmPage from '@/views/AdmPage.vue';
import AdmProductPage from '@/views/AdmProductPage.vue';
import CartPage from '@/views/CartPage.vue';
import NotFound from '@/views/NotFoundPage.vue';
import localStorageService from '@/services/localStorage'; // Para verificar o token

// Função para verificar se o usuário está autenticado
const isAuthenticated = () => {
  return !!localStorageService.getToken(); // Retorna true se houver um token
};

// Função para redirecionar para a página de login se não estiver autenticado
const requiresAuth = (to, from, next) => {
  if (!isAuthenticated()) {
    next({ name: 'Login' }); // Redireciona para o login
  } else {
    next(); // Permite o acesso à rota
  }
};

// Definindo as rotas
const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    // Não precisa de verificação de autenticação, pois é pública
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    // Não precisa de verificação de autenticação
  },
  {
    path: '/products',
    name: 'ProductPage',
    component: ProductPage,
  },
  {
    path: '/local',
    name: 'LocalPerson',
    component: LocalPerson,
  },
  {
    path: '/recovery',
    name: 'RecuperationPage',
    component: RecuperationPage,
    // Não precisa de verificação de autenticação
  },
  {
    path: '/details/:id',
    name: 'DetailsPage',
    component: DetailsPage,
  },
  {
    path: '/profile',
    name: 'ProfilePage',
    component: ProfilePage,
    beforeEnter: requiresAuth, // Verifica se o usuário está autenticado
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound,
  },
  {
    path: '/adm',
    name: 'AdmPage',
    component: AdmPage,
    //beforeEnter: requiresAuth, // Verifica se o usuário está autenticado
  },
  {
    path: '/adm/products',
    name: 'AdmProductPage',
    component: AdmProductPage,
    //beforeEnter: requiresAuth, // Verifica se o usuário está autenticado
  },
  {
    path: '/cart',
    name: 'CartPage',
    component: CartPage,
  }
];

// Criando o roteador
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
