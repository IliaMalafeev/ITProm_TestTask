<template>

    <div class="single_list_item_card">

        <div>

            <div class="single_list_item_card_info">

                <div><strong>ID: </strong> {{ employee.id }}</div>
                <div><strong>Full Name: </strong> {{ employee.fullName }}</div>

            </div>
            
            <div class="single_list_item_card_info">

                <div v-if="employee.departmentDTO"><strong>Department: </strong> {{ employee.departmentDTO.title }}</div>
                <div v-else><strong>Department: </strong> Not assigned</div>
                
                <div v-if="employee.professionDTO"><strong>Profession: </strong> {{ employee.professionDTO.title }}</div>
                <div v-else><strong>Profession: </strong> Not assigned</div>
                
            </div>

        </div>

        <div class="single_list_item_card_btns">

            <CustomButton @click="$router.push(`/employees/${employee.id}`)">Open</CustomButton>

        </div>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { Employee } from '@/entities/Employee';

    export default defineComponent({

        name: 'EmployeeItem',

        props: {

            employee: { type: Object as PropType<Employee>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/SingleListItemStyles.css";

</style>