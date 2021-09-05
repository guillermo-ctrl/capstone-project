import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Login from "./pages/Login";



export default function App() {
    return (
            <Router>
                <Switch>
                    <Route path="/login">
                        <Login />
                    </Route>
                </Switch>
            </Router>

);
}
