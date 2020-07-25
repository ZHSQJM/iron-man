<template>
    <div>
         <mavon-editor
          @imgAdd="handleEditorImgAdd"
          @imgDel="handleEditorImgDel"
          v-model="content"/>
    </div>
</template>

<script>


export default {
    name: "IronEditor",
    props:['content'],
    data() {
        return {
            
        }
    },
    methods:{
        handleEditorImgAdd(){

        },
        handleEditorImgDel(){
              
        }
    }
}
</script>

<style scoped>

</style>