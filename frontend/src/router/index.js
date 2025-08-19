import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/HomePage.vue';
import Login from '@/views/LoginPage.vue';
import Register from '@/views/RegisterPage.vue';
import ProductPage from '@/views/ProductPage.vue';
import RecuperationPage from '@/views/RecuperationPage.vue';
import DetailsPage from '@/views/DetailsPage.vue';
import ProfilePage from '@/views/ProfilePage.vue';
import AboutPage from '@/views/AboutUs.vue';
import CookieNotice from '@/views/CookieNotice.vue';
import AboutAds from '@/views/AboutAds.vue';
import UseConditions from '@/views/UseConditions.vue';
import WarningPrivacity from '@/views/WarningPrivacity.vue';
import ContactPage from '@/views/ContactPage.vue';
import CartPage from '@/views/CartPage.vue';
import NotFound from '@/views/NotFoundPage.vue';
import localStorageService from '@/services/LocalStorageService';

const isAuthenticated = () => {
  const token = localStorageService.getToken();
  return !!token;
};

const requiresAuth = (to, from, next) => {
  if (!isAuthenticated()) {
    next({ name: 'Login' });
  } else {
    next();
  }
};

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/login', name: 'Login', component: Login },
  { path: '/register', name: 'Register', component: Register },
  { path: '/products', name: 'ProductPage', component: ProductPage },
  { path: '/recovery', name: 'RecuperationPage', component: RecuperationPage },
  { path: '/details/:id', name: 'DetailsPage', component: DetailsPage },
  { path: '/cart', name: 'CartPage', component: CartPage},
  { path: '/profile', name: 'ProfilePage', component: ProfilePage, beforeEnter: requiresAuth },
  { path: '/cookies', name: 'CookieNotice', component: CookieNotice },
  { path: '/ads', name: 'AboutAds', component: AboutAds },
  { path: '/useconditions', name: 'UseConditions', component: UseConditions },
  { path: '/privacy', name: 'WarningPrivacity', component: WarningPrivacity },
  { path: '/contact', name: 'ContactPage', component: ContactPage },
  { path: '/aboutus', name: 'AboutPage', component: AboutPage },
  { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
