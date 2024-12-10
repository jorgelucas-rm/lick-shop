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
import AboutPage from '@/views/AboutUs.vue';
import CookieNotice from '@/views/CookieNotice.vue';
import AboutAds from '@/views/AboutAds.vue';
import UseConditions from '@/views/UseConditions.vue';
import WarningPrivacity from '@/views/WarningPrivacity.vue';
import ContactPage from '@/views/ContactPage.vue';
import CartPage from '@/views/CartPage.vue';
import NotFound from '@/views/NotFoundPage.vue';
import localStorageService from '@/services/localStorage'; 


// Função para verificar se o usuário está autenticado
const isAuthenticated = () => {
  return authStore.isLoggedIn; // Usa o estado do authStore para verificar se está logado
};

// Função para verificar se o usuário é administrador
const isAdmin = () => {
  return authStore.isAdmin; // Usa o estado do authStore para verificar se é admin
};

// Função para redirecionar para a página de login se não estiver autenticado
const requiresAuth = (to, from, next) => {
  if (!isAuthenticated()) {
    next({ name: 'Login' }); // Redireciona para o login
  } else {
    next(); // Permite o acesso à rota
  }
};

// Função para redirecionar para a página de login se o usuário não for admin
const requiresAdmin = (to, from, next) => {
  if (!isAdmin()) {
    next({ name: 'Home' }); // Redireciona para a Home se não for admin
  } else {
    next(); // Permite o acesso à rota de admin
  }
};

// Função para verificar se o usuário está acessando seu próprio perfil
const requiresCorrectProfile = (to, from, next) => {
  const token = localStorageService.getToken();
  if (!token) return next({ name: 'Login' }); // Redireciona para o login se não autenticado
  
  const decodedToken = JSON.parse(atob(token.split('.')[1])); // Decodificando o JWT
  const userId = decodedToken.id; // Obtém o id do usuário do token
  
  if (to.params.id === userId || isAdmin()) {
    next(); // Permite o acesso se o ID no perfil for igual ao ID do usuário logado ou se for admin
  } else {
    next({ name: 'Home' }); // Redireciona para a Home se o usuário tentar acessar o perfil de outro usuário
  }
};

// Definindo as rotas
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
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
  },
  {
    path: '/details/:id',
    name: 'DetailsPage',
    component: DetailsPage,
  },
  {
    path: '/profile/:id',
    name: 'ProfilePage',
    component: ProfilePage,
    beforeEnter: [requiresAuth, requiresCorrectProfile], // Verifica se é o próprio usuário ou um administrador
  },
  {
    path: '/adm',
    name: 'AdmPage',
    component: AdmPage,
    beforeEnter: [requiresAuth, requiresAdmin], // Verifica se é admin
  },
  {
    path: '/adm/products',
    name: 'AdmProductPage',
    component: AdmProductPage,
    beforeEnter: [requiresAuth, requiresAdmin], // Verifica se é admin
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound,
  },
  {
    path: '/cookies',
    name: 'CookieNotice',
    component: CookieNotice,
  },
  {
    path: '/ads',
    name: 'AboutAds',
    component: AboutAds,
  },
  {
    path: '/useconditions',
    name: 'UseConditions',
    component: UseConditions,
  },
  {
    path: '/privacy',
    name: 'WarningPrivacity',
    component: WarningPrivacity,
  },
  {
    path: '/contact',
    name: 'ContactPage',
    component: ContactPage,
  },
  {
    path: '/aboutus',
    name: 'AboutPage',
    component: AboutPage,
  },
  {
    path: '/cookies',
    name: 'CookieNotice',
    component: CookieNotice,
  },
  {
    path: '/ads',
    name: 'AboutAds',
    component: AboutAds,
  },
  {
    path: '/useconditions',
    name: 'UseConditions',
    component: UseConditions,
  },
  {
    path: '/privacy',
    name: 'WarningPrivacity',
    component: WarningPrivacity,
  },
  {
    path: '/contact',
    name: 'ContactPage',
    component: ContactPage,
  },
  {
    path: '/aboutus',
    name: 'AboutPage',
    component: AboutPage,
  },
  {
    path: '/cart',
    name: 'CartPage',
    component: CartPage,
    beforeEnter: [requiresAuth, requiresCorrectProfile],
  }
];

// Criando o roteador
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
