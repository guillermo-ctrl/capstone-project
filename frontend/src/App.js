import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Login from "./pages/Login";
import Browse from "./pages/Browse";
import AuthProvider from "./auth/AuthProvider";



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
