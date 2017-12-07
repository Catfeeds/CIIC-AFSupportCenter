###礼品API接口

1.1 查询礼品信息

**http方法:** get  
**api地址:**  
* 开发环境: http://localhost:6019/api/gift/findById/1
* 本地联调环境：http://10.17.2.236:6019/api/gift/findById/1
* SIT环境: http://172.16.9.25:6019/api/gift/findById/1
* UAT环境：  
* 生产环境：

响应报文--成功
```json
{
    "errorcode": "200",
    "errormsg": "",
    "data": {
        "id": 1,
        "giftName": "test",
        "price": 100,
        "rightPerson": 2,
        "giftType": 3,
        "color": "红色",
        "number": 123,
        "applyMaxnum": 20,
        "pictureUrl": "测试",
        "remarks": "tt测试2017年12月1日18:26:33",
        "status": 0,
        "isNew": true,
        "isActive": true,
        "createTime": 1510126947000,
        "modifiedTime": 1510126947000,
        "createdBy": "xi",
        "modifiedBy": "xi"
    }
}
```

响应报文--失败（没有相应信息）
```json
{
    "errorcode": "400",
    "errormsg": "没有相应数据",
    "data": null
}
```

1.2 更新礼品数量

**http方法:** post  
**api地址:**  
* 开发环境: http://localhost:6019/api/gift/updateById
* 本地联调环境：http://10.17.2.236:6019/api/gift/updateById
* SIT环境: http://172.16.9.25:6019/api/gift/updateById
* UAT环境：
* 生产环境：

请求报文
```json
{
  "id":7,
  "num":10
}
```

响应报文--成功
```json
{
    "errorcode": "200",
    "errormsg": "",
    "data": ""
}
```

响应报文--失败（没有更新数据）
```json
{
    "errorcode": "400",
    "errormsg": "数据没有更新",
    "data": ""
}
```

###市场活动

2. 查询市场活动数据

**http方法:** get  
**api地址:**  
* 开发环境: http://localhost:6019/api/market/findById/1
* 本地联调环境：http://10.17.2.236:6019/api/market/findById/1
* SIT环境: http://172.16.9.25:6019/api/market/findById/1
* UAT环境：  
* 生产环境：

响应报文
```json
{
    "errorcode": "200",
    "errormsg": "",
    "data": {
        "id": 1,
        "activityTitle": "测试1111",
        "publisher": "xwz",
        "beginTime": 1512489600000,
        "endTime": 1515340800000,
        "status": 0,
        "content": "测试2017年12月4日15:31:38",
        "giftForm": "3,2,1",
        "sendWay": "0",
        "isActive": true,
        "createTime": 1510815463000,
        "modifiedTime": 1512529099000,
        "createdBy": "测试",
        "modifiedBy": "测试"
    }
}
```

```json
{
    "errorcode": "400",
    "errormsg": "没有相应数据",
    "data": null
}
```
