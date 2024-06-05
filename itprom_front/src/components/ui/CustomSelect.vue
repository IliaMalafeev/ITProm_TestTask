<template>

    <select class="select" :value="modelValue" @change="changeOption">

        <option value="" disabled>Choose {{ fieldName }}</option>

        <option v-for="option in options" :key="option.id" :value="option.id">{{ option.title }}</option>

    </select>

</template>

<script lang="ts">

    import { DepartmentDTO } from '@/entities/DepartmentDTO';
    import { ProfessionDTO } from '@/entities/ProfessionDTO';
    import { PropType, defineComponent } from 'vue';

    export default defineComponent({

        name: 'CustomSelect',

        props: {

            fieldName: { type: String },

            modelValue: { type: String },

            options: { type: Array as PropType<DepartmentDTO[]> | PropType<ProfessionDTO[]> }

        },

        methods: {

            changeOption(event: any) {
                
                this.$emit('update:modelValue', event.target.value); 
            
            }

        }
        
    })

</script>

<style scoped>

    .select {
        width: 100%;
        border: 1px solid teal;
        border-radius: 6px;
        padding: 15px 15px;
        font-size: 1rem;
        transition: all 0.2s;
    }

    .select:focus{
        outline: 2px solid teal;
    }

</style>