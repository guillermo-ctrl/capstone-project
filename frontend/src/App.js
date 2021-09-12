import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Login from "./pages/Login";
import AuthProvider from "./auth/AuthProvider";
import Browse from "./pages/Browse";



export default function App() {
    return (
        <AuthProvider>
            <Router>
                <Switch>
                    <Route path="/login" component = {Login} />
                    <Route path="/browse" component={Browse} />
                </Switch>
            </Router>
        </AuthProvider>
);
}
