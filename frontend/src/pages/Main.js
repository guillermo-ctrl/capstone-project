import Navigation from "../components/Navigation";
import {Link, Redirect} from "react-router-dom";
import {useAuth} from "../auth/AuthProvider";
import MainPageButton from "../components/MainPageButton";
import Page from "../components/Page";
import styled from "styled-components/macro";

export default function Main () {
    const { user } = useAuth()

    if (!user) {
        return <Redirect to="/login" />
    }

    return (
        <Page>
            <Navigation user = {user}/>
            <MainPageContent>
                <MainPageButton url = "/browse" linkText = "Browse documents"></MainPageButton>
                <MainPageButton url = "/upload" linkText = "Upload a document"></MainPageButton>
            </MainPageContent>
        </Page>

    )
}

const MainPageContent = styled.div`
    height: auto;
`