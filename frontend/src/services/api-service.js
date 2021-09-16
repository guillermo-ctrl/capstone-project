import axios from "axios";

export const getToken = (credentials) =>
    axios.post("api/capstone-project/auth/access-token", credentials)
    .then(response => response.data)
    .then(dto => dto.token)

//here we weill also put the functions to update the password, delete the user etc.

export const getAllUserDocs = (ownerid) =>
    axios.get(`api/capstone-project/image/getallimages/${ownerid}`)
        .then(response => response.data)

export const getUserByUserName = (userName) =>
    axios.get(`/api/capstone-project/data/username/${userName}`)
        .then(response => response.data)

export const getDocumentById = (imageId) =>
    axios.get(`/api/capstone-project/image/getimagebyimageid/${imageId}`)