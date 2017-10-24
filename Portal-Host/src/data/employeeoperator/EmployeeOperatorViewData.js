/**
 * Created by huangye on 2017/10/23.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/employeeOperatorView').reply(200, {
  data: {



  }
})
const employeeOperatorView = Axios.get('employeeOperatorView')

export default {
  employeeOperatorView
}
