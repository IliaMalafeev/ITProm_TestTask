<template>

    <div class="modal" v-if="show" >

        <div class="modal__content" @click.stop>

            <button class="close__btn" @click="hideModal">X</button>

            <slot></slot>

        </div>

    </div>

</template>

<script lang="ts">

    import { defineComponent } from 'vue';

    export default defineComponent({

        name: 'CustomModal',

        props: {

            show: { type: Boolean, default: false }

        },

        methods: {

            hideModal() { this.$emit('update:show', false) }

        }

    });

</script>

<style scoped>

    .modal {
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        background: rgba(0, 76, 76, 0.6);
        position: fixed;
        display: flex;
    }

    .modal__content {
        position: relative;
        margin: auto;
        background: white;
        border-radius: 12px;
        width: 40%;
        padding: 30px;
        font-size: 1.25rem;
    }

    .close__btn {
        position: absolute;
        top: 20px;
        right: 20px;
        width: 40px;
        height: 40px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 2px solid teal;
        border-radius: 50%;
        font-size: large;
        color: rgb(0, 88, 88);
        background: rgb(217, 255, 255);
        cursor: pointer;
        transition: all 0.2s;
    }

    .close__btn:hover {
        box-shadow: 4px 4px 10px rgba(0, 128, 128, 0.3);
    }

    .close__btn:active {
        box-shadow: none;
    }

</style>