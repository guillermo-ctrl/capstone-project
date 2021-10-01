import Navigation from "../components/Navigation";
import {Link, Redirect} from "react-router-dom";
import {useAuth} from "../auth/AuthProvider";
import MainPageButton from "../components/MainPageButton";
import styled from "styled-components/macro";
import MainPage from "../components/MainPage";

export default function Main () {
    const { user } = useAuth()

    if (!user) {
        return <Redirect to="/login" />
    }

    return (
        <MainPage>
            <Navigation user = {user}/>
            <MainPageContent>
                <MainPageButton url = "/browse" linkText = "Browse documents"/>
                <MainPageButton url = "/upload" linkText = "Upload a document"/>
            </MainPageContent>
        </MainPage>

    )
}

const MainPageContent = styled.div`
    height: auto;
`