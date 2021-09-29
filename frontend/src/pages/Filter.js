import Page from "../components/Page";
import Navigation from "../components/Navigation";
import {useAuth} from "../auth/AuthProvider";
import DocumentGallery from "../components/DocumentGallery";
import {useEffect, useState} from "react";
import {getSomeUserDocs} from "../services/api-service";
import FilterForm from "../components/FilterForm";
import BackButton from "../components/BackButton";
import {useHistory} from "react-router-dom";

export default function Filter () {

    const {token, user} = useAuth()
    const {error, setError} = useState()
    const [filteredDocuments, setFilteredDocuments] = useState([])
    const [filterParams, setFilterParams] = useState([])
    const [category, setCategory] = useState()
    const [date, setDate] = useState()
    const [documentType, setDocumentType] = useState()
    const [language, setLanguage] = useState()
    const [recipient, setRecipient] = useState()
    const [sender, setSender] = useState()
    const [physicalLocation, setPhysicalLocation] = useState()

    const handleCategoryChange = event => {
        setCategory(event.target.value)
    }
    const handleDateChange = event => {
        setDate(event.target.value)
    }
    const handleDocumentTypeChange = event => {
        setDocumentType(event.target.value)
    }
    const handleLanguageChange = event => {
        setLanguage(event.target.value)
    }
    const handleRecipientChange = event => {
        setRecipient(event.target.value)
    }
    const handleSenderChange = event => {
        setSender(event.target.value)
    }
    const handlePhysicalLocationChange = event => {
        setPhysicalLocation(event.target.value)
    }

    useEffect(() => {
        if(filterParams.length > 0) {
            console.log(filterParams)
            getSomeUserDocs(token, filterParams)
                .then(setFilteredDocuments)
                .catch(error => {
                setError(error)
            })
        }
    }, [filterParams])

    const history = useHistory();
    const handleBack = event => {
        history.push("/browse")
    }

    const handleSubmit = event => {
        event.preventDefault()
        const createdFilterParams = []

        if (category) {createdFilterParams.push({category: category})}
        if (date) {createdFilterParams.push({date: date})}
        if (documentType) {createdFilterParams.push({documentType: documentType})}
        if (language) {createdFilterParams.push({language: language})}
        if (recipient) {createdFilterParams.push({recipient: recipient})}
        if (sender) {createdFilterParams.push({sender: sender})}
        if (physicalLocation) {createdFilterParams.push({physicalLocation: physicalLocation})}

        setFilterParams(createdFilterParams)

    }

    return (
            <Page>
                <Navigation user = {user}/>
                <FilterForm onSubmit={handleSubmit}>

                    <div>
                    <label>Category:</label>
                    <input onChange={handleCategoryChange} type = "text"/>
                    </div>

                    <div>
                    <label>Date:</label>
                    <input onChange={handleDateChange} type = "text"/>
                    </div>

                    <div>
                    <label>Document Type:</label>
                    <input onChange={handleDocumentTypeChange} type = "text"/>
                    </div>

                    <div>
                    <label>Language:</label>
                    <input onChange={handleLanguageChange} type = "text"/>
                    </div>

                    <div>
                    <label>Recipient:</label>
                    <input onChange={handleRecipientChange} type = "text"/>
                    </div>

                    <div>
                    <label>Sender:</label>
                    <input onChange={handleSenderChange} type = "text"/>
                    </div>

                    <div>
                    <label>Physical location:</label>
                    <input onChange={handlePhysicalLocationChange} type = "text"/>
                    </div>

                    <div>
                    <input type = "submit" value = "Filter"/>
                    </div>
                <BackButton onClick = {handleBack}>Back</BackButton>
                </FilterForm>
                <DocumentGallery allDocuments = {filteredDocuments}/>
            </Page>

    )
}