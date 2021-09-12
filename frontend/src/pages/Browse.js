import {useAuth} from "../auth/AuthProvider";
import Page from "../components/Page";
import Navigation from "../components/Navigation";
import Button from "../components/Button";

export default function Browse() {
    const {user} = useAuth()

    return (
        <Page>
            <Navigation user = {user}/>
            <Button>Filter</Button>
            <h1>User Documents here</h1>
        </Page>

    )
}