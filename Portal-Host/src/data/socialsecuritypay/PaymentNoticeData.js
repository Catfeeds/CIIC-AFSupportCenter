/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/paymentNotice').reply(200, {
  data: {
    companyName: '欧莱雅分公司1',
    companySocialSecurityAccount: '1144513312',
    noticeData: [
      {
        index: '1',
        project: '单位应缴纳社保费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '2',
        project: '单位应补缴历年社保费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '3',
        project: '个人应缴纳社会保险费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '4',
        project: '个人应补缴历月社会保险费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '5',
        project: '其他应缴纳社会保险费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '6',
        project: '预缴社保保险费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '7',
        project: '',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '8',
        project: '单位缓缴社保保险费',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      },
      {
        index: '9',
        project: '缴纳合计（1+2+3+4+5+6-8）',
        basePensionInsurance: '',
        baseMedicalInsurance: '',
        areaAddMedicalInsurance: '',
        unemploymentInsurance: '',
        injuryInsurance: '',
        fertilityInsurance: ''
      }
    ],
    shouldPayAmount: '',
    changeAmount: '',
    applyAmountLower: '',
    applyAmountUpper: '',
    notes: '滞纳金 232.99元'
  }
})
const paymentNotice = Axios.get('paymentNotice')

export default {
  paymentNotice
}
