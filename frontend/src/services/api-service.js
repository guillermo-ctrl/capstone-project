import axios from "axios";

export const getToken = (credentials) =>
    axios.post("api/capstone-project/auth/access-token", credentials)
    .then(response => response.data)
    .then(dto => dto.token)

//here we weill also put the functions to update the password, delete the user etc.