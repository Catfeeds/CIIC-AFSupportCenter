<template>
  <div class="x-bar clearfix charts">
    <div class="normalLayOutWarp">
      <div class="normalLayOut">
        <div :id="bar" :option="optionBar"></div>
      </div>
      <div class="normalLayOut">
        <div :id="circle" :option="optionCircle"></div>
      </div>
      <div class="normalLayOut withSelect">
        <div class="formSelect">
          <Form :model="formItem" :label-width=80>
            <Form-item label="薪资组">
              <Select v-model="formItem.select" placeholder="请选择">
                <Option value="a">薪资组A</Option>
                <Option value="b">薪资组B</Option>
                <Option value="c">薪资组C</Option>
                <Option value="d">薪资组D</Option>
                <Option value="e">薪资组E</Option>
              </Select>
            </Form-item>
          </Form>
        </div>
        <div :id="trend" :option="optionTrend"></div>
      </div>
      <div class="normalLayOut">
        <div :id="columnar" :option="optionColumnar"></div>
      </div>
    </div>
    <div class="clear"></div>
  </div>
</template>
<script>
  import HighCharts from 'highcharts'
  // 导入chart组件模拟数据
  import options from '../../static/js/options'
  export default {
    data() {
      let optionBar = options.bar
      let optionColumnar = options.columnar
      let optionTrend = options.trend
      let optionCircle = options.circle
      return {
        bar: 'bar',
        columnar: 'columnar',
        trend: 'trend',
        circle: 'circle',
        optionBar: optionBar,
        optionCircle: optionCircle,
        optionTrend: optionTrend,
        optionColumnar: optionColumnar,
        formItem: {
          input: '',
          select: '',
        }
      }
    },
    // 验证类型
    // props: {
    //     id: {
    //         type: String
    //     },
    //     option: {
    //         type: Object
    //     }
    // },
    mounted() {
      HighCharts.chart(this.bar, this.optionBar)
      HighCharts.chart(this.circle, this.optionCircle)
      HighCharts.chart(this.trend, this.optionTrend)
      HighCharts.chart(this.columnar, this.optionColumnar)
    }
  }

</script>
<style scoped>
  .normalLayOut {
    width: 45%;
    float: left;
    margin: 2% 2.5%;
  }

  .withSelect {
    position: relative;
  }

  .withSelect .formSelect {
    width: 40%;
    position: absolute;
    top: 10px;
    right: 0;
    z-index: 777;
  }
  /*去除图标的背景  */
</style>
