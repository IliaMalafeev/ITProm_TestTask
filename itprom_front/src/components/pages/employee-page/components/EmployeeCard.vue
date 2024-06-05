<template>
    
    <div class="item_page_card">

        <p class="item_page_card_field"><strong>Full Name: </strong> {{ employee.fullName }}</p>

        <p class="item_page_card_field"><strong>Info: </strong> {{ employee.additionalInfo }}</p>

        <p class="item_page_card_field" v-if="profession"><strong>Profession: </strong> {{ profession.title }}</p>
        <p class="item_page_card_field" v-else><strong>Profession: </strong> Not assigned</p>

        <p class="item_page_card_field" v-if="profession"><strong>Profession Info: </strong> {{ profession.description }}</p>
        <p class="item_page_card_field" v-else><strong>Profession Info: </strong> Not assigned</p>


        <div class="conditional_fields" v-if="department">
        
            <p class="item_page_card_field"><strong>Department: </strong> {{ department.title }}</p>

            <p class="item_page_card_field"><strong>Department Info: </strong> {{ department.additionalInfo }}</p>
            
            <p class="item_page_card_field" v-if="department.parentDepartment"><strong>Parent Dept: </strong> {{ department.parentDepartment.title }}</p>
            <p class="item_page_card_field" v-else><strong>No parent Department</strong></p>
        
        </div>
        
        <p class="item_page_card_field" v-else><strong>Department: </strong> Not assigned</p>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { Employee } from '@/entities/Employee'
    import { ProfessionDTO } from '@/entities/ProfessionDTO';
    import { DepartmentDTO } from '@/entities/DepartmentDTO';

    export default defineComponent({

        name: 'EmployeeCard',

        props: {

            employee:   { type: Object as PropType<Employee>, required: true },
            profession: { type: Object as PropType<ProfessionDTO | null> },
            department: { type: Object as PropType<DepartmentDTO | null> }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageCardStyles";

    .conditional_fields {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

</style>