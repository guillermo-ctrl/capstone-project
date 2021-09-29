import axios from "axios";
import {useParams} from "react-router-dom";

export const getToken = (credentials) =>
    axios.post("api/capstone-project/auth/access-token", credentials)
    .then(response => response.data)
    .then(dto => dto.token)

//here we weill also put the functions to update the password, delete the user etc.

const headers = token => ({
    headers: {
        Authorization: `Bearer ${token}`,
    },
})

export const getAllUserDocs = (token) =>
    axios.get(`/api/capstone-project/image/getallimages`, headers(token))
        .then(response => response.data)

export const getSomeUserDocs = (token, paramArray) => {
    axios.post(`/api/capstone-project/image/get-filtered-images`, paramArray, headers(token))
        .then(response => response.data)
}

export const getUserByUserName = (token, userName) =>
    axios.get(`/api/capstone-project/data/username/${userName}`, headers(token))
        .then(response => response.data)

export const getDocumentById = (token, imageId) =>
    axios.get(`/api/capstone-project/image/getimagebyimageid/${imageId}`, headers(token))

export const uploadDocumentToCloudinary = (token, formData) =>
    axios
        .post('/api/capstone-project/image/upload_to_cloud', formData, headers(token))
        .then(response => response.data)

export const saveDocumentInDocumentRepo = (token, image) =>
    axios
        .post(`api/capstone-project/image/save_in_database`, image, headers(token))
        .then(response => response.data)

export const getIdByUsername = (token, userName) =>
    axios.get(`/api/capstone-project/data/getUserId/${userName}`, headers(token))
        .then(response => response.data)

export const updateDocument = (token, document) =>
    axios
        .put(`/api/capstone-project/image/update`, document, headers(token))
        .then(response => response.data)