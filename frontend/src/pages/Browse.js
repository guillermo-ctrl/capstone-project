import {useAuth} from "../auth/AuthProvider";
import Page from "../components/Page";
import Navigation from "../components/Navigation";
import Button from "../components/Button";
import DocumentGallery from "../components/DocumentGallery";
import {getAllUserDocs, getUserByUserName} from "../services/api-service";
import {Redirect} from "react-router-dom";
import {useEffect, useState} from "react";
import Loading from "../components/Loading";
import Error from "../components/Error";

export default function Browse() {
    const { user } = useAuth()
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()
    const [allDocuments, setAllDocuments] = useState([])
    const [currentUser, setCurrentUser] = useState(false)

    useEffect(()=>{
        if(!user) {
            return <Redirect to ="/login"/>
        }
        setLoading(true)
        setError()
        console.log(user.username)
        getUserByUserName(user.username)
            .then(setCurrentUser)
            .catch(error => {
            setError(error)
        }).finally(() => setLoading(false))
        }, [user]
    )

    console.log(currentUser)

    useEffect(() =>{
        setLoading(true)
        setError()
        getAllUserDocs(currentUser.id)
            .then(setAllDocuments)
            .catch(error => {
            setError(error)
        }).finally(() => setLoading(false))
        }, [currentUser]
    )

    if (!user) {
        return <Redirect to="/login" />
    }

    return (
        <Page>
            <Navigation user = {user}/>
            <Button>Filter</Button>
            {loading && <Loading />}
            {error && <Error>{error.message}</Error>}
            <DocumentGallery allDocuments={allDocuments} />
        </Page>

    )
}