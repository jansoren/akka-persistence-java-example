/* eslint-disable max-len */
import axios from 'axios';

const hostname = 'http://localhost:8080/monitoring';


export const getEventLog = () => axios.get(`${hostname}/eventlog`);
