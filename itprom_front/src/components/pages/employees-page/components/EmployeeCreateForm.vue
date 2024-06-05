<template>

    <form class="item_create_form" @submit.prevent>

        <h3>Create Employee</h3>

        <div class="item_create_form_fields" v-if="!isCreateEmployeeLoading">

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="fullName" />
                <CustomInput v-model="employee.fullName" type="text" placeholder="Full Name" />
            </div>

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="additionalInfo" />
                <CustomInput v-model="employee.additionalInfo" type="text" placeholder="Additional Info" />
            </div>

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="departmentDTO" />
                <CustomSelect v-if="!isDepartmentsLoading" v-model="assignedDepartmentId" :options="departments" fieldName="Department" />
                <div v-else>Loading departments...</div>
            </div>
            
            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="professionDTO" />
                <CustomSelect v-if="!isProfessionsLoading" v-model="assignedProfessionId" :options="professions" fieldName="Profession" />
                <div v-else>Loading professions...</div>
            </div>

        </div>

        <div v-else>Creating Employee...</div>

        <CustomButton class="item_create_form_btn" :disabled="isCreateEmployeeLoading" @click="createEmployee">Create Employee</CustomButton>    

    </form>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import { useFetchDepartments } from '@/hooks/departments-fetchers/useFetchDepartments';
    import { useFetchProfessions } from '@/hooks/professions-fetchers/useFetchProfessions';
    import { useFetchCreateEmployee } from '@/hooks/employees-fetchers/useFetchCreateEmployee';

    export default defineComponent({

        name: 'EmployeeCreateForm',

        setup() {

            const { departments, isDepartmentsLoading } = useFetchDepartments();
            const { professions, isProfessionsLoading } = useFetchProfessions();

            const assignedDepartmentId = ref("");
            const assignedProfessionId = ref("");

            const { employee, isCreateEmployeeLoading, httpError, fetchCreateEmployee } = useFetchCreateEmployee();

            return { departments, isDepartmentsLoading, professions, isProfessionsLoading, assignedDepartmentId, 
                assignedProfessionId, employee, isCreateEmployeeLoading, httpError, fetchCreateEmployee };

        },

        methods: {
            
            async createEmployee() {

                const departmentToAssign = this.departments.find((el) => { return el.id === Number(this.assignedDepartmentId); });
                const professionToAssign = this.professions.find((el) => { return el.id === Number(this.assignedProfessionId); });

                if (departmentToAssign) { this.employee.departmentDTO = departmentToAssign; }
                if (professionToAssign) { this.employee.professionDTO = professionToAssign; }

                await this.fetchCreateEmployee(this.employee);

                if (!this.httpError) { this.$emit('create'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemCreateFormStyles.css";

</style>