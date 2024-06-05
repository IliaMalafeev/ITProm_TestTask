<template>
    
    <div class="item_page_card">

        <p class="item_page_card_field"><strong>Department title: </strong> {{ department.title }}</p>

        <p class="item_page_card_field"><strong>Department description: </strong> {{ department.additionalInfo }}</p>

        <p class="item_page_card_field" v-if="department.parentDepartment"><strong>Parent Department: </strong> {{ department.parentDepartment.title }}</p>
        <p class="item_page_card_field" v-else><strong>Parent Department: </strong> Not assigned</p>

        <p class="item_page_card_field" v-if="department.parentDepartment"><strong>Parent Department Info: </strong> {{ department.parentDepartment.additionalInfo }}</p>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { DepartmentDTO } from '@/entities/DepartmentDTO';

    export default defineComponent({

        name: 'DepartmentCard',

        props: {

            department: { type: Object as PropType<DepartmentDTO>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageCardStyles";

</style>