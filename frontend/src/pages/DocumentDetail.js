import Page from "../components/Page";

export default function DocumentDetails ({...props}) {
    console.log(window.location.pathname)
    return (
        <Page>
            <h1>document details</h1>
        </Page>

    )


}