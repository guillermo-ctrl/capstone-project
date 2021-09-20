import {useRef, useState} from "react";
import styled from 'styled-components/macro'
import {uploadDocument} from "../services/api-service";
export function DocumentUpload() {
    const inputRef = useRef()
    const [status, setStatus] = useState('ready')
    const [error, setError] = useState('')

    const handleSubmit = event => {
        event.preventDefault()
        setStatus('loading')
        const file = inputRef.current.files[0]
        uploadDocument(file)
            .then(() => setStatus('ready'))
            .catch(error => {
                console.log("error")
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
            {isError && <span>{error.message}</span>}
        </Wrapper>
    )
}

const Wrapper = styled.form`

`