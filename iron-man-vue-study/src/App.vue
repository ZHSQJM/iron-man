<template>
  <div id="app">
   <baidu-map class="map-container" v-bind="mapOptions" @ready="mapReady" @load="mapLoad" style="height:1000px;" >
    <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"/>
    <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_LEFT"/>
    <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"/>
</baidu-map>
      
  </div>
</template>

<script>
// import IronEditor from './components/IronEditor.vue'
import { BaiduMap, BmScale, BmMapType, BmNavigation } from 'vue-baidu-map/components'
export default {
  name: 'App',
  components: {
      BaiduMap, BmScale, BmMapType, BmNavigation
  },
    data(){
      return{
          mapOptions: {
            ak: '4Qv6OPQ1wlI1n8HGqZjqq6XNovnLfl9b',
            center: '南京栖霞区',
            
            scrollWheelZoom: true
         }
      }
    },
    methods:{
      mapReady (e) { 
        // this.center.lng = 118.919981
        // this.center.lat = 32.083972
       console.log("map ready"+e.BaiduMap);
     },
     mapLoad(e) {
    console.log("map load"+e);
    
   
  }
    }
}
</script>

<style scoped>

</style>
