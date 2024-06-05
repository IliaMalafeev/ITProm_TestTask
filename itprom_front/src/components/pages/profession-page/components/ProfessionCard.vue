<template>
    
    <div class="item_page_card">

        <p class="item_page_card_field"><strong>Profession title: </strong> {{ profession.title }}</p>

        <p class="item_page_card_field"><strong>Profession description: </strong> {{ profession.description }}</p>

    </div>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { ProfessionDTO } from '@/entities/ProfessionDTO';

    export default defineComponent({

        name: 'ProfessionCard',

        props: {

            profession: { type: Object as PropType<ProfessionDTO>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageCardStyles";

</style>