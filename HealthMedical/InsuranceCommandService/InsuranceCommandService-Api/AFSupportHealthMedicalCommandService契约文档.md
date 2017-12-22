###健康医疗API接口

1.1 新增医疗关系转移
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/MedicalRelationTransform/save
* UAT环境：  
* 生产环境：

请求报文：
employeeId:155
turnOutDate:2017-12-05
turnOutAddress:43435
turnBackDate:2017-12-05
remark:sdfdser4324111


响应报文--成功
```json
{
    "code": 200,
    "message": "操作成功",
    "data": "1"
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


1.2 更新医疗关系转移
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/MedicalRelationTransform/edit
* UAT环境：  
* 生产环境：
请求报文：
employeeId:155
turnOutDate:2017-12-05
turnOutAddress:43435
turnBackDate:2017-12-05
remark:sdfdser4324111
transformId:18

响应报文--成功
```json
{
    "code": 200,
    "total": 1,
    "message": "操作成功",
    "data": null
}

```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "total": 0,
    "message": "无数据更新",
    "data": null
}
```



1.3 新增零星报销
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement/save
* UAT环境：  
* 生产环境：
请求报文：
employeeId:15521
caseMoney:150
invoiceNumber:6
medicalRemark:2017213213WRWR
medicalClearingMoney:100
medicalCle1aringFeedBack:123

响应报文--成功
```json
{
    "code": 200,
    "total": 1,
    "message": "操作成功",
    "data": null
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


1.4 更新零星报销
**http方法:** POST  
**api地址:**  
* SIT环境: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement/edit
* UAT环境：  
* 生产环境：
请求报文：
id:1
employeeId:15521
caseMoney:150
invoiceNumber:6
medicalRemark:2017213213WRWR
medicalClearingMoney:100
medicalCle1aringFeedBack:123

响应报文--成功
```json
{
    "code": 200,
    "total": 1,
    "message": "操作成功",
    "data": null
}
```
响应报文--失败（没有相应信息）
```json
{
    "code": 400,
    "total": 0,
    "message": "无数据更新",
    "data": null
}
```


