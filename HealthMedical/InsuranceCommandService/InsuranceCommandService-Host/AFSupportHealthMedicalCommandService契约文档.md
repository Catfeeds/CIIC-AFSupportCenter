###����ҽ��API�ӿ�

1.1 ����ҽ�ƹ�ϵת��
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/MedicalRelationTransform/save
* UAT������  
* ����������

�����ģ�
employeeId:155
turnOutDate:2017-12-05
turnOutAddress:43435
turnBackDate:2017-12-05
remark:sdfdser4324111


��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "message": "�����ɹ�",
    "data": "1"
}
```
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```


1.2 ����ҽ�ƹ�ϵת��
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/MedicalRelationTransform/edit
* UAT������  
* ����������
�����ģ�
employeeId:155
turnOutDate:2017-12-05
turnOutAddress:43435
turnBackDate:2017-12-05
remark:sdfdser4324111
transformId:18

��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "total": 1,
    "message": "�����ɹ�",
    "data": null
}

```
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "total": 0,
    "message": "�����ݸ���",
    "data": null
}
```



1.3 �������Ǳ���
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement/save
* UAT������  
* ����������
�����ģ�
employeeId:15521
caseMoney:150
invoiceNumber:6
medicalRemark:2017213213WRWR
medicalClearingMoney:100
medicalCle1aringFeedBack:123

��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "total": 1,
    "message": "�����ɹ�",
    "data": null
}

```
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```


1.4 �������Ǳ���
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6015/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement/edit
* UAT������  
* ����������
�����ģ�
id:1
employeeId:15521
caseMoney:150
invoiceNumber:6
medicalRemark:2017213213WRWR
medicalClearingMoney:100
medicalCle1aringFeedBack:123

��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "total": 1,
    "message": "�����ɹ�",
    "data": null
}
```
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "total": 0,
    "message": "�����ݸ���",
    "data": null
}
```

