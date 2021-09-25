import styled from "styled-components/macro";
import BackButton from "./BackButton";
import {useAuth} from "../auth/AuthProvider";
import {useEffect, useState} from "react";

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

    useEffect(() => {
        if(document) {
            console.log("new document was set, a put request is coming")
        }
    }, [document])

    const handleCategoryChange = event => {
        setCategory(event.target.value)
        console.log(category)
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

        const otherDocument = JSON.parse(JSON.stringify(props.document))
        otherDocument.category = category
        otherDocument.date = date
        otherDocument.documentType = documentType
        otherDocument.language = language
        otherDocument.recipient = recipient
        otherDocument.sender = sender
        otherDocument.physicalLocation = physicalLocation

        setDocument(otherDocument)
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