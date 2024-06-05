<template>

    <div class="single_list_item_card">

        <div>

            <div class="single_list_item_card_info">

                <div><strong>ID: </strong> {{ profession.id }}</div>
                <div><strong>Title: </strong> {{ profession.title }}</div>

            </div>

            <div class="single_list_item_card_info">

                <div><strong>Description: </strong> {{ profession.description }}</div>

            </div>

        </div>

        <div class="single_list_item_card_btns">

            <CustomButton @click="$router.push(`/professions/${profession.id}`)">Open</CustomButton>

        </div>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { ProfessionDTO } from '@/entities/ProfessionDTO';

    export default defineComponent({

        name: 'ProfessionItem',

        props: {

            profession: { type: Object as PropType<ProfessionDTO>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/SingleListItemStyles.css";

</style>