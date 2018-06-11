<template>
  <v-layout row>
    <v-flex>
      <v-list>
        <v-list-group
          v-for="category in categories"
          v-model="category.active"
          :key="category.title"
          :prepend-icon="category.icon"
          no-action>
          <v-list-tile slot="activator">
            <v-list-tile-content>
              <v-list-tile-title>{{ category.title }}</v-list-tile-title>
            </v-list-tile-content>
            </v-list-tile>
            <v-list-tile v-for="subCategory in category.subCategories" :key="subCategory.title">
              <v-list-tile-content>
                <v-list-tile-title>{{ subCategory.title }}</v-list-tile-title>
              </v-list-tile-content>
              <v-list-tile-action><v-chip color="primary" text-color="white">{{ subCategory.amount + ' PLN' }}</v-chip></v-list-tile-action>
            </v-list-tile>
          </v-list-group>
        </v-list>
      </v-flex>
    </v-layout>
</template>

<script>
export default {
  name: 'CategoryList',
  data () {
    return {
      categories: [
        {
          icon: 'restaurant',
          title: 'Jedzenie',
          amount: 1500,
          subCategories: [
            { title: 'Słodycze', amount: 200.49 },
            { title: 'Codzienne', amount: 800 },
            { title: 'Na miescie', amount: 500 }
          ]
        },
        {
          icon: 'smoking_rooms',
          title: 'Używki',
          amount: 500,
          subCategories: [
            { title: 'Papierosy', amount: 350 },
            { title: 'Alkohol', amount: 150 }
          ]
        }
      ]
    }
  }
}
</script>
