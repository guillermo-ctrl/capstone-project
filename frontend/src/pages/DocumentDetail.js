import Page from "../components/Page";
import Navigation from "../components/Navigation";
import {useAuth} from "../auth/AuthProvider";
import {Redirect, useHistory, useParams} from "react-router-dom";
import SingleDocument from "../components/SingleDocument";
import {getDocumentById} from "../services/api-service";
import {useEffect, useState} from "react";
import Loading from "../components/Loading";
import DocumentProperties from "../components/DocumentProperties";
import BackButton from "../components/BackButton";


export default function DocumentDetails () {
    const { token, user } = useAuth()
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()
    const [currentDocument, setCurrentDocument] = useState(false)
    const history = useHistory();
    const {documentId} = useParams();



    useEffect(()=>{
        if(!user) {
            return <Redirect to ="/login"/>
        }
        setLoading(true)
        setError()
        getDocumentById(token, documentId.toString())
            .then(setCurrentDocument)
            .finally(() => setLoading(false))
        }, [user])

    const handleBack = event => {
        history.push("/browse")
            }

    const handleEdit = event => {
        history.push(`/edit/${documentId}`)
    }

    return (
        <Page>
            <Navigation user = {user}/>
            {loading && <Loading />}
            {currentDocument && (
                    <div>
                        <DocumentProperties document = {currentDocument.data} />
                        <BackButton onClick ={handleEdit} >Edit</BackButton>
                        <BackButton onClick ={handleBack} >Back to documents</BackButton>
                        <SingleDocument document={currentDocument.data}/>
                    </div>
            )}

        </Page>

    )

}