import styled from "styled-components/macro";
import BackButton from "./BackButton";
import {useAuth} from "../auth/AuthProvider";
import {useEffect, useState} from "react";
import {updateDocument} from "../services/api-service";

export function DocumentDetailsForm({...props}) {
    const { token, logout, user } = useAuth()
    const [category, setCategory] = useState(props.document.category)
    const [date, setDate] = useState(props.document.date)
    const [documentType, setDocumentType] = useState(props.document.documentType)
    const [language, setLanguage] = useState(props.document.language)
    const [recipient, setRecipient] = useState(props.document.recipient)
    const [sender, setSender] = useState(props.document.sender)
    const [physicalLocation, setPhysicalLocation] = useState(props.document.physicalLocation)
    const [document, setDocument] = useState()
    const [error, setError] = useState()

    useEffect(() => {
        if(document) {
            updateDocument(token, document)
                .catch(error => {
                    setError(error)
                })
        }
    }, [document])

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

    const handleSubmit = event => {
        event.preventDefault()

        const modifiedDocument = JSON.parse(JSON.stringify(props.document))
        modifiedDocument.category = category
        modifiedDocument.date = date
        modifiedDocument.documentType = documentType
        modifiedDocument.language = language
        modifiedDocument.recipient = recipient
        modifiedDocument.sender = sender
        modifiedDocument.physicalLocation = physicalLocation

        setDocument(modifiedDocument)
    }


    return (
        <Wrapper>

                <form onSubmit={handleSubmit}>

                    <label>Category:</label>
                    <input type = "text" defaultValue = {props.document.category} onChange={handleCategoryChange}/>

                    <label>Date:</label>
                    <input type = "text" defaultValue = {props.document.date} onChange={handleDateChange}/>

                    <label>Document Type:</label>
                    <input type = "text" defaultValue = {props.document.documentType} onChange={handleDocumentTypeChange}/>

                    <label>Language:</label>
                    <input type = "text" defaultValue = {props.document.language} onChange={handleLanguageChange}/>

                    <label>Recipient:</label>
                    <input type = "text" defaultValue = {props.document.recipient} onChange={handleRecipientChange}/>

                    <label>Sender:</label>
                    <input type = "text" defaultValue = {props.document.sender} onChange={handleSenderChange}/>

                    <label>Physical location:</label>
                    <input type = "text" defaultValue = {props.document.physicalLocation} onChange={handlePhysicalLocationChange}/>

                    <input type = "submit" value = "Save changes"/>

                </form>

        </Wrapper>
    )
}

const Wrapper = styled.div `
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-left: 10%;
  input {
    display: block;
  }
  

`