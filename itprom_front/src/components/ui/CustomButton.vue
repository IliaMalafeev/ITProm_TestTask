<template>
    
    <button class="btn">

        <slot></slot>

    </button>

</template>

<script lang="ts">

    import { defineComponent } from 'vue';

    export default defineComponent({

        name: 'CustomButton'

    });

</script>

<style scoped>

    .btn {
        padding: 15px 20px;
        background: none;
        border: 2px solid teal;
        border-radius: 6px;
        color: teal;
        font-size: 1rem;
        cursor: pointer;
        transition: all 0.2s;
    }

    .btn:hover {
        background: rgb(217, 255, 255);
        box-shadow: 4px 4px 10px rgba(0, 128, 128, 0.3);
    }

    .btn:active {
        background: rgb(217, 255, 255);
        box-shadow: none;
    }

    .btn:disabled {
        cursor: not-allowed;
        opacity: 0.6;
        background: none;
        box-shadow: none;
    }

</style>