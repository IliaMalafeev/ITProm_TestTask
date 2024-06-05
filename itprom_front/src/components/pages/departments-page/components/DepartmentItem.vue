<template>

    <div class="single_list_item_card">

        <div>

            <div class="single_list_item_card_info">

                <div><strong>ID: </strong> {{ department.id }}</div>
                <div><strong>Title: </strong> {{ department.title }}</div>

            </div>

            <div class="single_list_item_card_info">

                <div><strong>Description: </strong> {{ department.additionalInfo }}</div>
                <div v-if="department.parentDepartment"><strong>Parent Department: </strong> {{ department.parentDepartment.title }}</div>
                <div v-else><strong>Parent Department: </strong> Not Assigned</div>

            </div>

        </div>

        <div class="single_list_item_card_btns">

            <CustomButton @click="$router.push(`/departments/${department.id}`)">Open</CustomButton>

        </div>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { DepartmentDTO } from '@/entities/DepartmentDTO';

    export default defineComponent({

        name: 'DepartmentItem',

        props: {

            department: { type: Object as PropType<DepartmentDTO>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/SingleListItemStyles.css";

</style>