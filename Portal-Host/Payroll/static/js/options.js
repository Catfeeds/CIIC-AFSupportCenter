var chart = null;
module.exports = {
    bar: { //柱状图
        credits: {
            text: '',
            href: ''
        },
        chart: {
            type: 'column',
        },
        title: {
            text: '公司雇员平均收入排行榜'
        },
        subtitle: {
            // text: '点击可查看具体的版本数据，数据来源: <a href="https://netmarketshare.com">netmarketshare.com</a>.'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '年环比'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y}'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} (RMB)</b><br/>'
        },
        series: [{
            name: '公司名称',
            colorByPoint: true,
            data: [{
                name: '欧莱雅',
                y: 4500,
                drilldown: null
            }, {
                name: '雅诗莱黛',
                y: 5000,
                drilldown: null
            }, {
                name: '香奈儿',
                y: 6000,
                drilldown: null
            }, {
                name: '迪奥',
                y: 7500,
                drilldown: null
            }, {
                name: 'ciic',
                y: 8000,
                drilldown: null
            }]
        }]
    },
    circle: {
        credits: {
            text: '',
            href: ''
        },
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            spacing: [100, 0, 40, 0]
        },
        title: {
            floating: false,
            text: '按城市客户分布'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.y}家</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.y} 家',
                    style: {
                        //color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                point: {
                    events: {
                        mouseOver: function (e) {  // 鼠标滑过时动态更新标题
                            // 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
                            // chart.setTitle({
                            //     text: e.target.name+ '\t'+ e.target.y + ' %'
                            // });
                        }
                        //, 
                        // click: function(e) { // 同样的可以在点击事件里处理
                        //     chart.setTitle({
                        //         text: e.point.name+ '\t'+ e.point.y + ' %'
                        //     });
                        // }
                    }
                },
            }
        },
        series: [{
            type: 'pie',
            innerSize: '80%',
            name: '公司数',
            data: [
                { name: '上海', y: 38, url: '' },
                {
                    name: '成都',
                    y: 56,
                    sliced: true,
                    selected: true,
                    url: ''
                },
                ['深圳', 40],
                ['广州', 20],
                ['杭州', 12]
            ]
        }]
    },
    columnar: {
        credits: {
            text: '',
            href: ''
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '准确率趋势'
        },
        subtitle: {
            // text: '数据截止 2017-03，来源: <a href="https://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '准确率: <b>{point.y:.1f} %</b>'
        },
        series: [{
            name: '',
            data: [
                ['201701', 80],
                ['201702', 85],
                ['201703', 88],
                ['201704', 90],
                ['201705', 95],
                ['201706', 97],
                ['201707', 98]
            ],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                format: '{point.y:.1f}', // one decimal
                y: 10, // 10 pixels down from the top
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    },
    trend: {
        credits: {
            text: '',
            href: ''
        },
        title: {
            text: '薪资计算量趋势',
            x: -20,
        },
        // labels: {
        //     items: [{
        //         //标签代码（html代码）
        //         html: '<p style="font-size:20">薪资组：</p><Select placeholder="请选择"><Option value="beijing">北京市</Option><Option value="shanghai">上海市</Option><Option value="shenzhen">深圳市</Option></Select>',
        //         style: { //设置标签位置
        //             right: '0',
        //             top: '0',
        //         }
        //     }],
        //     style: {    //设置标签颜色
        //         color: 'rgb(0,0,255)'
        //     }
        // },
        xAxis: {
            categories: ['201701', '201702', '201703', '201704', '201705', '201706', '201707', '201708', '201709', '201710', '201711', '201712']
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '万/人次'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '计算量',
            data: [90, 91, 95, 96, 96, 96, 97, 99.1]
        }]
    }
}