import styled from "styled-components/macro";
import BackButton from "./BackButton";
import {useAuth} from "../auth/AuthProvider";
import {useEffect, useState} from "react";
import {updateDocument} from "../services/api-service";
import {useHistory, useParams} from "react-router-dom";

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
    const [readyToRedirect, setReadyToRedirect] = useState(false)
    const history = useHistory();


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

    useEffect(() =>{
        if(readyToRedirect) {
            history.push(`/details/${document.imageId}`)
        }
    }, [readyToRedirect])

    useEffect(() => {
        if(document) {
            updateDocument(token, document)
                .then(setReadyToRedirect)
                .catch(error => {
                    setError(error)
                })

        }
    }, [document])

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
                    <div>
                    <label>Category:</label>
                    <input type = "text" defaultValue = {props.document.category} onChange={handleCategoryChange}/>
                    </div>

                    <div>
                    <label>Date:</label>
                    <input type = "text" defaultValue = {props.document.date} onChange={handleDateChange}/>
                    </div>

                    <div>
                    <label>Document Type:</label>
                    <input type = "text" defaultValue = {props.document.documentType} onChange={handleDocumentTypeChange}/>
                    </div>

                    <div>
                    <label>Language:</label>
                    <input type = "text" defaultValue = {props.document.language} onChange={handleLanguageChange}/>
                    </div>

                    <div>
                    <label>Recipient:</label>
                    <input type = "text" defaultValue = {props.document.recipient} onChange={handleRecipientChange}/>
                    </div>

                    <div>
                    <label>Sender:</label>
                    <input type = "text" defaultValue = {props.document.sender} onChange={handleSenderChange}/>
                    </div>

                    <div>
                    <label>Physical location:</label>
                    <input type = "text" defaultValue = {props.document.physicalLocation} onChange={handlePhysicalLocationChange}/>
                    </div>

                    <div>
                    <input type = "submit" value = "Save changes"/>
                    </div>

                </form>
        </Wrapper>
    )
}

const Wrapper = styled.div `
    input {
       display: block;
       padding: 5px;
    }
    input[type=submit] {
       display: block;
       width: 300px;
       margin: 20px auto;
       padding: 10px;
       border: 0;
       color: white;
       font-size: 1em;
       background: #15aabf;
    }
    
    div {
       padding: 10px;
    }
    
     display: flex;
     flex-wrap: wrap;
     justify-content: left;
     margin-left: 10%;
  
  
  
  
  

`