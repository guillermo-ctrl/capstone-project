import {useAuth} from "../auth/AuthProvider";
import Page from "../components/Page";
import Navigation from "../components/Navigation";
import DocumentGallery from "../components/DocumentGallery";
import {getAllUserDocs, getUserByUserName} from "../services/api-service";
import {Redirect, useHistory} from "react-router-dom";
import {useEffect, useState} from "react";
import Loading from "../components/Loading";
import Error from "../components/Error";
import BackButton from "../components/BackButton";

export default function Browse() {
    const { token, user } = useAuth()
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()
    const [allDocuments, setAllDocuments] = useState([])
    const history = useHistory();


    useEffect(() =>{
        setLoading(true)
        setError()
        if (user) {

        getAllUserDocs(token)
            .then(setAllDocuments)
            .catch(error => {
            setError(error)
        }).finally(() => setLoading(false))
        }
        }, [user]
    )

    if (!user) {
        return <Redirect to="/login" />
    }

    const handleBack = event => {
        history.push("/")
    }

    const handleFilter = event => {
        history.push("/filter")
    }

    return (
        <Page>
            <Navigation user = {user}/>
            <h1>My documents</h1>
            <BackButton onClick ={handleFilter} >Filter</BackButton>
            {loading && <Loading />}
            {error && <Error>{error.message}</Error>}
            <DocumentGallery allDocuments={allDocuments} />
            <BackButton onClick ={handleBack} >Back</BackButton>
        </Page>

    )
}