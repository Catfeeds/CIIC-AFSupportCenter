/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companyTaskProgressTypeInfo').reply(200, {
  data: {

  }
})
const companyTaskProgressTypeInfo = Axios.get('companyTaskProgressTypeInfo')

export default {
  companyTaskProgressTypeInfo
}
