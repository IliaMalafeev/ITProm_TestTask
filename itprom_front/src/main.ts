import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { uiComponents } from './components/ui';

const app = createApp(App);

uiComponents.forEach((item) => {app.component(item.name, item.component)});

app.use(router).mount('#app');
