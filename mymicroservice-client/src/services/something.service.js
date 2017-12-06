/* eslint-disable max-len */
import axios from 'axios';

const hostname = 'http://localhost:8080/something';


export const doSomething = () => axios.get(`${hostname}/do`);
export const getSomething = () => axios.get(`${hostname}/get`);
