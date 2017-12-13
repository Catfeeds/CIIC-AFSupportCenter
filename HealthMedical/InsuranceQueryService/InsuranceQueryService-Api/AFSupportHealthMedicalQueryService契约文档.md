###健康医疗API接口

1.1 查询医疗关系转移信息ById
**http方法:** GET  
**api地址:**  
* SIT环境: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/MedicalRelationTransform/getEntityById?id=2
* UAT环境：  
* 生产环境：
响应报文--成功
```json
{
    "code": 0,
    "message": null,
    "data": {
        "transformId": 2,
        "employeeId": "11H1234",
        "turnOutDate": [
            2017,
            12,
            9
        ],
        "turnOutAddress": "不知道什么地方2",
        "turnBackDate": [
            2017,
            12,
            5
        ],
        "remark": "yongyutianxiebeizhu",
        "createdTime": [
            15,
            42,
            39
        ],
        "modifiedTime": [
            15,
            42,
            39
        ],
        "createdBy": "123",
        "modifiedBy": "456",
        "active": true
    },
    "total": null
}
```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "message": "没有相应数据",
    "data": null,
    "total": 0
}
```


1.2 查询医疗关系转移信息
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/MedicalRelationTransform/getEntityList
* UAT环境：  
* 生产环境：
请求报文：
pageNum:1
pageSize:2
params['transformid']:200

响应报文--成功
```json
{
    "code": 200,
    "message": "操作成功",
    "data": [
        {
            "transformId": 2,
            "employeeId": "11H1234",
            "turnOutDate": [
                2017,
                12,
                9
            ],
            "turnOutAddress": "不知道什么地方2",
            "turnBackDate": [
                2017,
                12,
                5
            ],
            "remark": "yongyutianxiebeizhu",
            "createdTime": [
                15,
                42,
                39
            ],
            "modifiedTime": [
                15,
                42,
                39
            ],
            "createdBy": "123",
            "modifiedBy": "456",
            "active": true
        }
    ],
    "total": 1
}
```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "message": "没有相应数据",
    "data": null,
    "total": 0
}
```



1.3 查询零星报销信息ById
**http方法:** GET  
**api地址:**  
* SIT环境: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/FragmentaryReimbursement/getEntityById?id=2
* UAT环境：  
* 生产环境：
响应报文--成功
```json
{
    "code": 0,
    "message": null,
    "data": {
        "id": 2,
        "employeeId": "11H1234",
        "caseMoney": 200,
        "invoiceNumber": 3,
        "medicalRemark": "beizhuxi2nxi",
        "medicalClearingMoney": 200,
        "medicalClearingFeedBack": "4545",
        "createdTime": [
            15,
            45,
            55
        ],
        "modifiedTime": [
            15,
            45,
            55
        ],
        "createdBy": "123",
        "modifiedBy": "456",
        "active": true
    },
    "total": null
}

```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "message": "没有相应数据",
    "data": null,
    "total": 0
}
```


1.2 查询零星报销信息
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/FragmentaryReimbursement/getEntityList
* UAT环境：  
* 生产环境：
请求报文：
pageNum:1
pageSize:2
params['id']:2

响应报文--成功
```json
{
    "code": 200,
    "message": "操作成功",
    "data": [
        {
            "id": 2,
            "employeeId": "11H1234",
            "caseMoney": 200,
            "invoiceNumber": 3,
            "medicalRemark": "beizhuxi2nxi",
            "medicalClearingMoney": 200,
            "medicalClearingFeedBack": "4545",
            "createdTime": [
                15,
                45,
                55
            ],
            "modifiedTime": [
                15,
                45,
                55
            ],
            "createdBy": "123",
            "modifiedBy": "456",
            "active": true
        }
    ],
    "total": 1
}
```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "message": "没有相应数据",
    "data": null,
    "total": 0
}
```


