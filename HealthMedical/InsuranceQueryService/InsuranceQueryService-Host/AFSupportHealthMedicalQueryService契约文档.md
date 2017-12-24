###����ҽ��API�ӿ�

1.1 ��ѯҽ�ƹ�ϵת����ϢById
**http����:** GET  
**api��ַ:**  
* SIT����: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/MedicalRelationTransform/getEntityById?id=2
* UAT������  
* ����������
��Ӧ����--�ɹ�
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
        "turnOutAddress": "��֪��ʲô�ط�2",
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
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```


1.2 ��ѯҽ�ƹ�ϵת����Ϣ
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/MedicalRelationTransform/getEntityList
* UAT������  
* ����������
�����ģ�
pageNum:1
pageSize:2
params['transformid']:200

��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "message": "�����ɹ�",
    "data": [
        {
            "transformId": 2,
            "employeeId": "11H1234",
            "turnOutDate": [
                2017,
                12,
                9
            ],
            "turnOutAddress": "��֪��ʲô�ط�2",
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
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```



1.3 ��ѯ���Ǳ�����ϢById
**http����:** GET  
**api��ַ:**  
* SIT����: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/FragmentaryReimbursement/getEntityById?id=2
* UAT������  
* ����������
��Ӧ����--�ɹ�
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
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```


1.2 ��ѯ���Ǳ�����Ϣ
**http����:** POST  
**api��ַ:**  
* SIT����: http://172.16.9.24:6013/api/afsupportcenter/healthmedical/queryservice/FragmentaryReimbursement/getEntityList
* UAT������  
* ����������
�����ģ�
pageNum:1
pageSize:2
params['id']:2

��Ӧ����--�ɹ�
```json
{
    "code": 200,
    "message": "�����ɹ�",
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
��Ӧ����--ʧ�ܣ�û����Ӧ��Ϣ��
```json
{
    "code": 400,
    "message": "û����Ӧ����",
    "data": null,
    "total": 0
}
```

