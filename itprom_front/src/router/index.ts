import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import EmployeePage from '@/components/pages/employee-page/EmployeePage.vue'
import EmployeesPage from '@/components/pages/employees-page/EmployeesPage.vue'
import DepartmentPage from '@/components/pages/department-page/DepartmentPage.vue'
import DepartmentsPage from '@/components/pages/departments-page/DepartmentsPage.vue'
import ProfessionPage from '@/components/pages/profession-page/ProfessionPage.vue'
import ProfessionsPage from '@/components/pages/professions-page/ProfessionsPage.vue'

const routes: Array<RouteRecordRaw> = [

    { path: '/',                component: EmployeesPage    },
    { path: '/employees/:id',   component: EmployeePage     },
    { path: '/departments',     component: DepartmentsPage  },
    { path: '/departments/:id', component: DepartmentPage   },
    { path: '/professions',     component: ProfessionsPage  },
    { path: '/professions/:id', component: ProfessionPage   },

]

const router = createRouter({ 

    // history: createWebHistory(process.env.BASE_URL), 
    
    history: createWebHashHistory(process.env.BASE_URL), // // Changed defaults for avoiding 404 during refresh while served from docker
    routes 

})

export default router
