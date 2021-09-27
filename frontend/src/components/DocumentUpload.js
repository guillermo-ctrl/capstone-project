import {useEffect, useRef, useState} from "react";
import styled from 'styled-components/macro'
import {
    getIdByUsername,
    saveDocumentInDocumentRepo,
    uploadDocumentToCloudinary
} from "../services/api-service";
import {useAuth} from "../auth/AuthProvider";
import {useHistory} from "react-router-dom";

export function DocumentUpload() {
    const history = useHistory();
    const inputRef = useRef()
    const [status, setStatus] = useState('ready')
    const [error, setError] = useState('')
    const [uploadResponse, setUploadResponse] = useState()
    const [currentUser, setCurrentUser] = useState()
    const [imageWithId, setImageWithId] = useState()
    const { token, user } = useAuth()


    useEffect(() =>{
        if (imageWithId) {
            history.push(`/edit/${imageWithId.imageId}`)
        }
    }, [imageWithId])

    useEffect(()=>{
            if (currentUser) {
                const newImage = uploadResponse
                saveDocumentInDocumentRepo(token, newImage)
                    .then(setImageWithId)
                    .catch(error => {
                    setStatus('error')
                    setError(error)
                })
            }
        }, [currentUser]
    )

    useEffect(()=>{
        if (uploadResponse) {
            getIdByUsername(token, user.username)
                .then(setCurrentUser)
                .catch(error => {
                setStatus('error')
                setError(error)
            })
        }
        }, [uploadResponse]
    )

    const handleSubmit = event => {
        event.preventDefault()
        setStatus('loading')
        const file = inputRef.current.files[0]
        const formData = new FormData()
        formData.set('document', file)

        uploadDocumentToCloudinary(token, formData)
            .then(setUploadResponse)
            .then(() => setStatus('ready'))
            .catch(error => {
                setStatus('error')
                setError(error)
            })
        }

    const isError = status === 'error'
    const isLoading = status === 'loading'

    return (
        <Wrapper onSubmit={handleSubmit}>
            {isLoading ? (
                <span>loading</span>
            ) : (
                <>
                    <input type="file" ref={inputRef} />
                    <button>Upload</button>
                </>
            )}

            {uploadResponse &&
            <span>
                <p>Upload successful</p>
                <img src = {uploadResponse.url} alt = ""/>
            </span>
            }
            {isError && <span>{error.message}</span>}
        </Wrapper>
    )
}

const Wrapper = styled.form`
img {
    width: 100%;
}

`