import styled from 'styled-components/macro'
import Navigation from "../components/Navigation";
import {DocumentUpload} from "../components/DocumentUpload";
import BackButton from "../components/BackButton";
import {useHistory} from "react-router-dom";


export default function Upload() {
    const history = useHistory();
    const handleBack = event => {
        history.push("/")
    }

    return (
        <Wrapper>
            <Navigation/>
            <DocumentUpload />
            <BackButton onClick ={handleBack} >Back</BackButton>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: grid;
  text-align: center;
  grid-template-rows: min-content 1fr;
  padding-bottom: 64px;
`
