import axios from 'axios';

export default {
  call(endpoint) {
    let urlApi = `http://localhost:8081/api/${endpoint}`;

    return {
      getOne: ({ id }) => axios.get(`${urlApi}/${id}`),
      getAll: () => axios.get(`${urlApi}`),
      update: (toUpdate) =>  axios.put(urlApi,toUpdate),
      create: (toCreate) =>  axios.put(urlApi,toCreate),
      delete: ({ id }) =>  axios.delete(`${urlApi}/${id}`)
    }
  }
}