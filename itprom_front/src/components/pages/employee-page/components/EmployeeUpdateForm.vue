<template>

    <form class="item_update_form" @submit.prevent>

        <h3>Update Employee</h3>

        <div class="item_update_form_fields" v-if="!isUpdateEmployeeLoading">

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="fullName" />
                <CustomInput v-model="employee.fullName" type="text" placeholder="Full Name" />
            </div>

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="additionalInfo" />
                <CustomInput v-model="employee.additionalInfo" type="text" placeholder="Additional Info" />
            </div>

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="departmentDTO" />
                <CustomSelect v-if="!isDepartmentsLoading" v-model="assignedDepartmentId" :options="departments" fieldName="Department" />
                <div v-else>Loading departments...</div>
            </div>

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="professionDTO" />
                <CustomSelect v-if="!isProfessionsLoading" v-model="assignedProfessionId" :options="professions" fieldName="Profession" />
                <div v-else>Loading professions...</div>
            </div>

        </div>

        <div v-else>Updating Employee...</div>

        <CustomButton class="item_update_form_btn" :disabled="isUpdateEmployeeLoading" @click="updateEmployee">Update Employee</CustomButton>    

    </form>

</template>

<script lang="ts">

    import { PropType, defineComponent, ref } from 'vue';
    import { useFetchDepartments } from '@/hooks/departments-fetchers/useFetchDepartments';
    import { useFetchProfessions } from '@/hooks/professions-fetchers/useFetchProfessions';
    import { useFetchUpdateEmployee } from '@/hooks/employees-fetchers/useFetchUpdateEmployee';
    import { Employee } from '@/entities/Employee';

    export default defineComponent({

        name: 'EmployeeUpdateForm',

        props: {

            employee: { type: Object as PropType<Employee>, required: true },

        },

        setup(props) {

            const { departments, isDepartmentsLoading } = useFetchDepartments();
            const { professions, isProfessionsLoading } = useFetchProfessions();

            const assignedDepartmentId = ref(props.employee.departmentDTO ? String(props.employee.departmentDTO.id) : "");
            const assignedProfessionId = ref(props.employee.professionDTO ? String(props.employee.professionDTO.id) : "");

            const { isUpdateEmployeeLoading, httpError, fetchUpdateEmployee } = useFetchUpdateEmployee();

            return { departments, isDepartmentsLoading, professions, isProfessionsLoading, assignedDepartmentId, 
                assignedProfessionId, isUpdateEmployeeLoading, httpError, fetchUpdateEmployee };

        },

        methods: {
            
            async updateEmployee() {

                const departmentToAssign = this.departments.find((el) => { return el.id === Number(this.assignedDepartmentId); });
                const professionToAssign = this.professions.find((el) => { return el.id === Number(this.assignedProfessionId); });

                if (departmentToAssign) { this.employee.departmentDTO = departmentToAssign; }
                if (professionToAssign) { this.employee.professionDTO = professionToAssign; }

                await this.fetchUpdateEmployee(this.employee);

                if (!this.httpError) { this.$emit('update'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemUpdateFormStyles.css";

</style>