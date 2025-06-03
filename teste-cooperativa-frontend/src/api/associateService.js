import axios from 'axios';
import restConsumer from './restConsumer';

const associateService = {
  createAssociate: (associate) => axios.post('http://15.229.78.133:8081/associate', associate, Headers = {
    'Content-Type': 'application/json'
  }),
};

export default associateService;