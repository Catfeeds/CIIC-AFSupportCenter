###礼品API接口

1. 分页查询礼品列表数据

**http方法:** post  
**api地址:**  
* 开发环境: http://localhost:6019/api/gift/findById/1
* 本地联调环境：http://10.17.2.236:6019/api/gift/findById/1
* SIT环境: http://172.16.9.25:6019/api/gift/findById/1
* UAT环境：  
* 生产环境：

传入参数
```json
{
  "pegeNum":1,
  "pageSize":5,
  "giftType":2
}
```

响应报文--成功
```json
{
    "errorcode": "200",
    "errormsg": "",
    "data": {
        "pageNum": 1,
        "pageSize": 5,
        "size": 5,
        "startRow": 1,
        "endRow": 5,
        "total": 27,
        "pages": 6,
        "list": [
            {
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
                "modifiedBy": "xi",
                "page": null
            },
            {
                "id": 2,
                "giftName": "2017年11月16日18:42:02",
                "price": 231,
                "rightPerson": 0,
                "giftType": 0,
                "color": "红色",
                "number": 100,
                "applyMaxnum": 51,
                "pictureUrl": "",
                "remarks": "ceshi",
                "status": 1,
                "isNew": false,
                "isActive": true,
                "createTime": 1510829588000,
                "modifiedTime": 1510829588000,
                "createdBy": "xiweizhen",
                "modifiedBy": "ceshi",
                "page": null
            },
            {
                "id": 3,
                "giftName": "2017年11月16日18:42:02",
                "price": 231,
                "rightPerson": 0,
                "giftType": 0,
                "color": "红色",
                "number": 100,
                "applyMaxnum": 51,
                "pictureUrl": "",
                "remarks": "ceshi",
                "status": 0,
                "isNew": false,
                "isActive": true,
                "createTime": 1510831472000,
                "modifiedTime": 1510831472000,
                "createdBy": "xiweizhen",
                "modifiedBy": "ceshi",
                "page": null
            },
            {
                "id": 4,
                "giftName": "2017年11月16日18:42:02",
                "price": 231,
                "rightPerson": 0,
                "giftType": 0,
                "color": "红色",
                "number": 100,
                "applyMaxnum": 51,
                "pictureUrl": "",
                "remarks": "ceshi",
                "status": 0,
                "isNew": false,
                "isActive": true,
                "createTime": 1510886600000,
                "modifiedTime": 1510886600000,
                "createdBy": "xiweizhen",
                "modifiedBy": "ceshi",
                "page": null
            },
            {
                "id": 7,
                "giftName": "测试2017年12月6日11:02:25",
                "price": 231,
                "rightPerson": 1,
                "giftType": 2,
                "color": "红色",
                "number": 230,
                "applyMaxnum": 21,
                "pictureUrl": "baidu",
                "remarks": "测试2017年11月17日14:13:42",
                "status": 0,
                "isNew": true,
                "isActive": true,
                "createTime": 1510899216000,
                "modifiedTime": 1510899216000,
                "createdBy": "",
                "modifiedBy": "",
                "page": null
            }
        ],
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2,
            3,
            4,
            5,
            6
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 6,
        "firstPage": 1,
        "lastPage": 6
    }
}
```

###市场活动

2. 分页查询市场活动列表数据

**http方法:** post  
**api地址:**  
* 开发环境: http://localhost:6019/api/market/findMarketList
* 本地联调环境：http://10.17.2.236:6017/api/market/findMarketList
* SIT环境: http://172.16.9.25:6019/market/findMarketList
* UAT环境：  
* 生产环境：

传入参数
```json
{
  "pegeNum":1,
  "pageSize":5,
  "activityName":"",
  "publish":"",
  "status":2
}
```

响应报文
```json
{
    "errorcode": "200",
    "errormsg": "",
    "data": {
        "pageNum": 1,
        "pageSize": 5,
        "size": 5,
        "startRow": 1,
        "endRow": 5,
        "total": 15,
        "pages": 3,
        "list": [
            {
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
                "modifiedBy": "测试",
                "page": null
            },
            {
                "id": 2,
                "activityTitle": "ceshi",
                "publisher": "xwz",
                "beginTime": 1512372433000,
                "endTime": 1512372433000,
                "status": 0,
                "content": "测试",
                "giftForm": "1,2,3",
                "sendWay": "1,2",
                "isActive": true,
                "createTime": 1512372410000,
                "modifiedTime": 1512372410000,
                "createdBy": "",
                "modifiedBy": "",
                "page": null
            },
            {
                "id": 3,
                "activityTitle": "ceshi",
                "publisher": "xwz",
                "beginTime": 1512372500000,
                "endTime": 1512372500000,
                "status": 0,
                "content": "测试",
                "giftForm": "1,2,3",
                "sendWay": "1,2",
                "isActive": true,
                "createTime": 1512372476000,
                "modifiedTime": 1512372476000,
                "createdBy": "",
                "modifiedBy": "",
                "page": null
            },
            {
                "id": 4,
                "activityTitle": "测试",
                "publisher": "xwz",
                "beginTime": 1512372653000,
                "endTime": 1512372668000,
                "status": 0,
                "content": "测试2017年12月4日15:31:38",
                "giftForm": "1,2,3",
                "sendWay": "0,1",
                "isActive": true,
                "createTime": 1512372711000,
                "modifiedTime": 1512372711000,
                "createdBy": "",
                "modifiedBy": "",
                "page": null
            },
            {
                "id": 5,
                "activityTitle": "测试",
                "publisher": "xwz",
                "beginTime": 1512372653000,
                "endTime": 1512372668000,
                "status": 0,
                "content": "测试2017年12月4日15:31:38",
                "giftForm": "1,2,3",
                "sendWay": "0,1",
                "isActive": true,
                "createTime": 1512372736000,
                "modifiedTime": 1512372736000,
                "createdBy": "",
                "modifiedBy": "",
                "page": null
            }
        ],
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2,
            3
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 3,
        "firstPage": 1,
        "lastPage": 3
    }
}
```

