import TextField from "../components/TextField";
import Button from "../components/Button";
import Logo from "../components/Logo";
import LoginPage from "../components/LoginPage";
import logo from "../images/logo.png"


export default function Login() {

    return (

        <LoginPage>

            <Logo src={logo}/>

            <TextField
                title = "Username"
            />
            <TextField
                title = "Password"
            />
            <Button>Login</Button>
        </LoginPage>
    )
}