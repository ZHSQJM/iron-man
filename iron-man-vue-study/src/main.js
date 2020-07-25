import Vue from 'vue'
import App from './App.vue'
import store from './store'
import VideoPlayer from 'vue-video-player'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')

Vue.use(VideoPlayer)
Vue.config.productionTip = false

    // use
    Vue.use(mavonEditor)

new Vue({
  store,
  render: h => h(App)
}).$mount('#app')
