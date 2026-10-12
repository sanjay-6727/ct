<script setup>

import {onMounted, ref} from "vue"

const API ='http://localhost:8080/api/library'

const books=ref([])
const title=ref('')
const author=ref('')
const copiesAvailable=ref(1)
const error=ref('')

async function getBooks(){
try {
error.value=''
const response=await fetch(`${API}/all`)
if (!response.ok){
throw new Error('books couldnt be fetched')
}
books.value=await response.json()
}catch(err){
error.value=err.message
}
}

async function addBook(){
try{
error.value=''
const response=await fetch(API, {method:'POST', headers:{'Content-Type':'application/json'},
body:JSON.stringify({title:title.value, author:author.value, copiesAvailable:Number(copiesAvailable.value)
})
})

if(!response.ok){
throw new Error('failed to add book')
}
title.value=''
author.value=''
copiesAvailable.value=1

}catch(err){
error.value=err.message
}

}
onMounted(() => {
  getBooks()
})
</script>



<template>
<p> Main Page </p>

<form @submit.prevent="addBook">
<input v-model="title" placeholder="title" required/>
<input v-model="author" placeholder="author" required/>
<input v-model.number="copiesAvailable" placeholder="copies" type="number" min=0 required/>
<button type="submit">Add Book</button>

</form>
</template>

<style scoped>
</style>
