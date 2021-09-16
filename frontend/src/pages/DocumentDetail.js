import Page from "../components/Page";
import Navigation from "../components/Navigation";
import {useAuth} from "../auth/AuthProvider";
import {Redirect} from "react-router-dom";
import SingleDocument from "../components/SingleDocument";
import {getDocumentById} from "../services/api-service";
import {useEffect, useState} from "react";
import Loading from "../components/Loading";
import Error from "../components/Error";

export default function DocumentDetails () {
    const { user } = useAuth()
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()
    const [currentDocument, setCurrentDocument] = useState(false)

    // The following way to get the documentId is terrible and shameful and needs to be changed
    const path = window.location.pathname
    const documentId = path.substring(9, path.length)

    useEffect(()=>{
        if(!user) {
            return <Redirect to ="/login"/>
        }
        setLoading(true)
        setError()
        getDocumentById(documentId)
            .then(setCurrentDocument)
            .catch(error => {
                setError(error)
            }).finally(() => setLoading(false))
        }, [documentId])

    return (
        <Page>
            <Navigation user = {user}/>
            {loading && <Loading />}
            {error && <Error>{error.message}</Error>}
            {currentDocument && (
                    <SingleDocument document={currentDocument.data}/>
            )}
        </Page>

    )


}