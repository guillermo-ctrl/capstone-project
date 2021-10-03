import Page from "../components/Page";
import Navigation from "../components/Navigation";
import Loading from "../components/Loading";
import {useAuth} from "../auth/AuthProvider";
import {useEffect, useState} from "react";
import {Redirect, useHistory, useParams} from "react-router-dom";
import {getDocumentById} from "../services/api-service";
import {DocumentDetailsForm} from "../components/DocumentDetailsForm";
import styled from "styled-components/macro";

export default function DocumentEdit () {
    const { token, user } = useAuth()
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()
    const documentId = useParams();
    const [currentDocument, setCurrentDocument] = useState(false)

    useEffect(()=>{
        if(!user) {
            return <Redirect to ="/login"/>
        }
        setLoading(true)
        setError()
        getDocumentById(token, documentId.documentId)
            .then(setCurrentDocument)
            .finally(() => setLoading(false))
    }, [user])

    return (
        <Page>
            <Navigation user = {user}/>
            {loading && <Loading />}
            {currentDocument && (
                <Wrapper>
                    <DocumentDetailsForm document = {currentDocument.data} />
                    <img src = {currentDocument.data.url} alt = ""/>
                </Wrapper>
            )}
        </Page>
    )
}

const Wrapper = styled.div `
  img {
    width: 100%
  }
  
`