<template>
  <div>
    <v-layout row>
      <v-btn flat small v-on:click="callRestService">REST</v-btn>
    </v-layout>
    <v-layout row>
      <v-dialog
          ref="dialog"
          v-model="modal"
          :return-value.sync="date"
          persistent
          lazy
          fullscreen
          full-width
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <v-text-field
            slot="activator"
            v-model="date"
            prepend-icon="event"
            readonly
          ></v-text-field>

          <v-date-picker v-model="date" type="month" @input="$refs.dialog.save(date)">
          </v-date-picker>
        </v-dialog>

    </v-layout>
    <v-layout row>
      <category-list></category-list>
    </v-layout>
  </div>

</template>

<script>
import CategoryListVue from './CategoryList.vue'

import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      modal: false,
      date: null
    }
  },
  components: {
    'category-list': CategoryListVue
  },
  methods: {
    callRestService: function () {
      axios.get('/api/test')
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>
