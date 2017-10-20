<template>
  <div class="buttons">
      <div class="previewDiv" style="display:inline-block">
        <Button type="info"  @click="back">返回</Button>
      </div>
      <div class="preview">
        <p>
            <img src="../../assets/payrllpreview.jpg" alt="">
        </p>
      </div>
  </div>
</template>
<<script>
export default {
    methods: {
      back() {
        this.$local.back();
      }
    }
}
</script>

<style scoped>
    .preview p{ width: 100%; text-align: center; height: 100px; line-height: 100px; font-size: 20px; margin-top: 2%;}
</style>
