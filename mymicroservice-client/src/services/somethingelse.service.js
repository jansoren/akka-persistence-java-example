/* eslint-disable max-len */
import axios from 'axios';

const hostname = 'http://localhost:8080/somethingelse';


export const doSomethingElse = () => axios.get(`${hostname}/do`);
export const getSomethingElse = () => axios.get(`${hostname}/get`);
