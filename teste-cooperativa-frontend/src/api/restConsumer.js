import axios from 'axios';

const restConsumer = axios.create({
  baseURL: 'http://15.229.78.133:8081/',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default restConsumer;
