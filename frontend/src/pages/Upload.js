import DocumentUpload from "../components/DocumentUpload";
import styled from 'styled-components/macro'
import {useAuth} from "../auth/AuthProvider";
import {Link, Redirect, useHistory} from "react-router-dom";
import Page from "../components/Page";
import Navigation from "../components/Navigation";
import BackButton from "../components/BackButton";

export default function Upload() {

    const { user } = useAuth()
    const history = useHistory();

    if (!user) {
        return <Redirect to="/login" />
    }

    const handleBack = event => {
        history.push("/")
    }

    return (
        <Page>
            <Navigation user = {user}/>
            <h1>Upload document</h1>
            <BackButton onClick ={handleBack} > Back </BackButton>
        </Page>

    )
}

const Wrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: grid;
  text-align: center;
  grid-template-rows: min-content 1fr;
  padding-bottom: 64px;
`
