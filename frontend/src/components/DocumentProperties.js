import styled from 'styled-components/macro'

export default function DocumentProperties({...props}) {

    return (
        <Wrapper {...props}>
            <ul>
                <li>Category: {props.document.category}</li>
                <li>Date: {props.document.date}</li>
                <li>Document type: {props.document.document_type}</li>
                <li>Language: {props.document.language}</li>
                <li>Recipient: {props.document.recipient}</li>
                <li>Sender: {props.document.sender}</li>
                <li>Physical location: {props.document.location}</li>
            </ul>
        </Wrapper>
    )
}

const Wrapper = styled.div `
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-left: 10%;
  list-style-type: none;
  
  ul {
    list-style-type: none;
  }
  
`