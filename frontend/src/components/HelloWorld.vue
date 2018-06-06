<template>
  <v-container fluid>
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
            label="Picker in dialog"
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
  </v-container>

</template>

<script>
import CategoryListVue from './CategoryList.vue'

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
  }
}
</script>
