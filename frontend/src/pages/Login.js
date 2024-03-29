import TextField from "../components/TextField";
import Button from "../components/LoginButton";
import Logo from "../components/Logo";
import LoginPage from "../components/LoginPage";
import logo from "../images/logo.png"
import {useState} from "react";
import {Redirect} from "react-router-dom";
import Loading from "../components/Loading";
import Error from "../components/Error";
import {useAuth} from "../auth/AuthProvider";
import LoginForm from "../components/LoginForm";

const initialState = {
    username: '',
    password: '',
}

export default function Login() {

    const { login, user } = useAuth()
    const [credentials, setCredentials] = useState(initialState)
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState()

    const handleCredentialsChange = event =>
        setCredentials({...credentials, [event.target.name]: event.target.value })

    const handleSubmit = event => {
        event.preventDefault()
        setLoading(true)
        setError()
        login(credentials).catch(error => {
            setError(error)
            setLoading(false)
        })
    }

    if (user) {
        return <Redirect to="/" />
    }
    document.body.style.height = `100vh`

    return (
        <LoginPage>
            <Logo src={logo}/>
            {loading && <Loading />}
            {!loading && (
            <LoginForm onSubmit={handleSubmit}>
                <TextField
                    title = "Username"
                    type = "text"
                    name = "username"
                    value ={credentials.username}
                    onChange = {handleCredentialsChange}
                />
                <TextField
                    title = "Password"
                    type = "password"
                    name = "password"
                    value ={credentials.password}
                    onChange = {handleCredentialsChange}
                />
                <Button>Login</Button>
            </LoginForm>
            )}
            {error && <Error>Invalid user name or password.</Error>}
        </LoginPage>
    )
}