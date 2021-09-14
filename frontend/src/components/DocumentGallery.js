import SingleDocument from './SingleDocument'
import styled from 'styled-components/macro'

export default function DocumentGallery({allDocuments, ...props }) {



    return (
        <Wrapper {...props}>
            {allDocuments.map(document => (
                <SingleDocument document = {document} key = {document.url}/>
            ))
            }
        </Wrapper>
    )
}

const Wrapper = styled.div `
  display: flex;
  flex-wrap: wrap;
  justify-content: center;


  SingleDocument {
    margin: 12px;
  }

`